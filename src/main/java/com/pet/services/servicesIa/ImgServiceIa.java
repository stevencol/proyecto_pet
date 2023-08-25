package com.pet.services.servicesIa;

import com.pet.entitys.Imgs;

import java.util.List;

public interface ImgServiceIa {

     void save(List<Imgs> imgs);

    List<Imgs> findAll();

    void delteById(Long id);
    Imgs findById(Long id);

    public void deleteByIdCloud(String idCloud);
}
