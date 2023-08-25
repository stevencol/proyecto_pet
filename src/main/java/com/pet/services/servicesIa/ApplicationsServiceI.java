package com.pet.services.servicesIa;

import com.pet.entitys.Applications;

import java.util.List;

public interface ApplicationsServiceI  {

    List<Applications> findAll();

    Applications save(Applications applications);

    Applications findById(Long id);

    void deleteById(Long id);

    public List <Applications> findApicationForPersonById(Long id);

    int findByPendingForIdPerson(Long id);


    List<Applications>  findAllbyStatus(String status);
    List<Applications>  findByStatusAnUserId( Long id ,String status);




}
