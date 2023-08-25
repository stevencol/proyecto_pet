package com.pet.services.servicesImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pet.entitys.Imgs;
import com.pet.entitys.Pet;
import com.pet.mappers.MapperStruct;
import com.pet.services.servicesIa.CloudinariService;
import com.pet.services.servicesIa.ImgServiceIa;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
@Data
public class CloudinariServiceImpl implements CloudinariService {

    @Autowired
    private ImgServiceIa imgService;
    @Autowired
    private MapperStruct map;


    private Cloudinary cloudinary;


    public CloudinariServiceImpl() {


       cloudinary = new Cloudinary(System.getenv("CLOUDINARY_URL"));

    }


    @Override
    public ResponseEntity<?> setImgPEt(List<MultipartFile> imgs, Pet pet) {

        Map<String, Object> response = new HashMap<>();
        List<Imgs> urls = new ArrayList<>();


        if(checkFormat(imgs)){
            response.put("message" ,"Formato no permitido");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Map params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", true
               /// "folder", "pets/pet:" + pet.getId().toString()
        );

        try {

            for (MultipartFile img : imgs) {
                Imgs image = new Imgs();
                image.setPet(pet);
                String url = cloudinary.uploader().upload(img.getBytes(), params).get("secure_url").toString();
                image.setUrls(url);
                image.setIdCloud(cloudinary.uploader().upload(img.getBytes(), params).get("public_id").toString());

                Map<Object, Object>  datos= cloudinary.uploader().upload(img.getBytes(), params);

                urls.add(image);



            }

            imgService.save(urls);

            response.put("message" , "Se guardaron las Imagenes Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            response.put("message", "Error en guardar  el archivo : " + e.getCause());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public ResponseEntity<?> getImg() {
        return null;
    }


    private Boolean checkFormat(List<MultipartFile> imgs){
        List<String> formats = Arrays.asList(".jpg", ".jpeg", ".png", ".gif");

        for(MultipartFile file : imgs){
            String filename = file.getOriginalFilename();
                if(!formats.contains(filename.substring(filename.lastIndexOf("."), filename.length()))){
                    return  true;
                }

        }
        return  false;
    }

    public ResponseEntity<?> delete(String idCloud){


        Map<String , Object> response = new HashMap<>();
        try {
            Map<String , Object> result=  cloudinary.uploader().destroy(idCloud ,ObjectUtils.emptyMap());
            if(result.get("result").equals("ok")){
                imgService.deleteByIdCloud(idCloud);
                response.put("message" ,"Se elimino correctamente");
            return     new ResponseEntity<>(response , HttpStatus.OK);
            }else if(result.get("result").equals("not found")){
                response.put("message" ,"No exite el resgistro");
                return     new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
            }else {
                response.put("message" ,"No exite el resgistro");
                return     new ResponseEntity<>(result , HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            response.put("message" ,"Se prodijo un error al elimniar" + e.getCause());
          return   new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    public ResponseEntity<?> deleteFloder(Long id)  {




        Map<String , Object> response = new HashMap<>();
        try {

            Map<String , Object> result=  cloudinary.api().deleteResourcesByPrefix("pets/pet:" +id, ObjectUtils.emptyMap());

            if(result.get("deleted")!=null){
                response.put("result" , cloudinary.api().deleteFolder("/pets/pet:" +id, ObjectUtils.emptyMap()));
                response.put("message" ,"Se elimino correctamente");
                return     new ResponseEntity<>(response , HttpStatus.OK);
            }else if(result.get("result").equals("not found")){
                response.put("message" ,"No exite el resgistro");
                return     new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
            }else {
                response.put("message" ,"No exite el resgistro");
                return     new ResponseEntity<>(result , HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.put("message" ,"Error al eliminar la carpeta:  " + e);
            return     new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
        }
    }




}
