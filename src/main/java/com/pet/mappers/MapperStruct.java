package com.pet.mappers;

import com.pet.dtos.*;
import com.pet.entitys.*;


import java.util.List;



public interface MapperStruct {

    Pet savePet(PetPostDto pet);

    List<PetGetDto> getPets(List<Pet> pet);

    Raza saveRaza (RaceDtoSave race);
    PetGetDto getPet(Pet pet);

    RaceDtoAll getRace(Raza race);
    List<RaceDtoAll> getRaces(List<Raza> race);

    List <PetTypeAll> getTypes(List<PetType> types);

    PetType saveType(PetTypeDtoName type);

    PetTypeDtoName getType(PetType type);

    PetType getTypeId(PetTypeUseId type);

    Person savePerson(PersoPostnDto personDto);


    PersonGetPostSImple getPerson(Person person);
    List<PersonGetPostSImple> getPersons(List <Person> person);

    PersonGetId getIdPerson(Person person);

     List<ApplicationsGetDto> getAplications(List <Applications> applications);


    ApplicationsGetDto getAplication(Applications applications);


     Applications saveAplication(ApplicationsPostDto application);


}
