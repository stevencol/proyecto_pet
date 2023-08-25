package com.pet.services.servicesImpl;

import com.pet.entitys.Applications;
import com.pet.enums.ApplicationStatus;
import com.pet.repository.ApplicationsRepo;
import com.pet.services.servicesIa.ApplicationsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationsServiceImpl  implements ApplicationsServiceI {

    @Autowired
    private ApplicationsRepo appRepo;

    @Override
    public List<Applications> findAll() {
        return appRepo.findAll();
    }

    @Override
    public Applications save(Applications applications) {

     if(applications.getId()==null){
         applications.setStatus(ApplicationStatus.PENDING);
         return   appRepo.save(applications);
     }
        return   appRepo.save(applications);
    }

    @Override
    public Applications findById(Long id) {
       return  appRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List <Applications> findApicationForPersonById(Long id) {
        return appRepo.findByIdPerson(id);
    }

    @Override
    public int findByPendingForIdPerson(Long id) {
        return appRepo.finCounByStatus(id);
    }

    @Override
    public List<Applications> findAllbyStatus(String status) {
      return   appRepo.findAllbyStatus(ApplicationStatus.valueOf(status));
    }

    @Override
    public List<Applications> findByStatusAnUserId( Long id , String status) {
        return appRepo.findAllbyStatusAndPersonId(id , ApplicationStatus.valueOf(status));
    }
}
