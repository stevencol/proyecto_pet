package com.pet.services.servicesImpl;

import com.pet.dtos.ImgDelete;
import com.pet.entitys.Imgs;
import com.pet.repository.ImgRepo;
import com.pet.services.servicesIa.ImgServiceIa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImgSercieImpl  implements ImgServiceIa {

    @Autowired
    private ImgRepo imgRepo;
    @Transactional
    @Override
    public void save(List<Imgs> imgs) {
        imgRepo.saveAllAndFlush(imgs);

    }

    @Override
    public List<Imgs> findAll() {
        return null;
    }

    @Override
    public void delteById(Long id) {
    imgRepo.deleteById(id);
    }

    @Override
    public Imgs findById(Long id) {
        return null;
    }

    @Override
    public void deleteByIdCloud(String idCloud) {


        if(imgRepo.findByIdCloud(idCloud)!=null){
                delteById(imgRepo.findByIdCloud(idCloud).getId());

        }



    }
}
