package com.pet.services.servicesIa;



import com.pet.entitys.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinariService {
    public ResponseEntity<?> setImgPEt(List<MultipartFile> imgs , Pet pet);

    ResponseEntity<?> getImg();

}

