package com.pet.mappers;

import com.pet.dtos.*;
import com.pet.entitys.*;
import com.pet.enums.ApplicationStatus;
import com.pet.enums.Sexo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class MapperImpl implements  MapperStruct{


    @Override
    public Pet savePet(PetPostDto pet) {

        if(pet == null){
            return  null;
        }

        Pet petEntity = new Pet();
        Raza razaEntity  = new Raza();

        List<Imgs> imgs = new ArrayList<>();

        razaEntity.setId(pet.getRace().getId());
        PetType type = new PetType();
        type.setId(pet.getType().getId());

        petEntity.setName(pet.getName());
        petEntity.setAge(pet.getAge());
        petEntity.setRace(razaEntity);
        petEntity.setType(type);
        petEntity.setSexo(Sexo.valueOf(pet.getSex()));


      if(pet.getImg()!=null){
          pet.getImg().forEach(object->{
              Imgs img = new Imgs();;
              img.setPet(petEntity);
              img.setUrls(object.getUrls());
              imgs.add(img);
          });
          petEntity.setImg(imgs);
      }

        petEntity.setDescription(pet.getDescription());
        System.out.println(petEntity.getType());

        return petEntity;

    }

    @Override
    public List <PetGetDto> getPets(List<Pet> pets) {

        if(pets == null){
            return null;
        }

        List <PetGetDto> petsFinal = new ArrayList<>();

        pets.forEach(pet->{
            PetGetDto petDto = new PetGetDto();
            petDto.setName(pet.getName());
            petDto.setId(pet.getId());
            petDto.setEdad(pet.getAge());
            if(pet.getImg()!=null){
                List<ImgsForPetGet> urls = new ArrayList<>();
                pet.getImg().forEach(url->{
                    ImgsForPetGet im = new ImgsForPetGet(url.getUrls());
                    im.setIdCloud(url.getIdCloud());
                    urls.add(im);

                });
                petDto.setImg(urls);
            }

            petDto.setSexo(pet.getSexo());
            petDto.setPetType(new PetTypeDtoName(pet.getType().getName()));
            petDto.setDescription(pet.getDescription());
            petDto.setRace(new RaceDtoName(pet.getRace().getName()));
            petsFinal.add(petDto);
        });



        return petsFinal;
    }


    @Override
    public PetGetDto getPet(Pet pet){

        if(pet==null){
            return  null;
        }

        RaceDtoName race = new RaceDtoName(pet.getRace().getName());
        PetTypeDtoName type = new PetTypeDtoName(pet.getType().getName());
        PetGetDto petF = new PetGetDto();
        petF.setId(pet.getId());
        petF.setName(pet.getName());
        petF.setEdad(pet.getAge());
        petF.setSexo(pet.getSexo());
        petF.setRace(race);
        petF.setPetType(type);
       if(pet.getImg()!=null){
           List<ImgsForPetGet> urls = new ArrayList<>();
           pet.getImg().forEach(url->{
               ImgsForPetGet petDto = new ImgsForPetGet(url.getUrls());
               petDto.setIdCloud(url.getIdCloud());
               urls.add(petDto);
           });
           petF.setImg(urls);
       }
        petF.setDescription(pet.getDescription());

        return  petF;

    }

    @Override
    public Raza saveRaza(RaceDtoSave race) {

        if(race==null){
            return  null;

        }



        Raza raceEntity = new Raza();
        raceEntity.setType(getTypeId(race.getType()));
        raceEntity.setName(race.getName());
        return raceEntity;

    }


    @Override
    public RaceDtoAll getRace(Raza race) {

        if(race==null){
            return  null;

        }
        RaceDtoAll raceDto = new RaceDtoAll();
        raceDto.setName(race.getName());
        raceDto.setId(race.getId());
        return raceDto;

    }

    @Override
    public List<RaceDtoAll> getRaces(List<Raza> race) {

        if(race==null){
            return  null;

        }
        List<RaceDtoAll>  races= new ArrayList<>();

        race.forEach(p->{
            races.add(getRace(p));
        });

        return  races;
    }

    @Override
    public List<PetTypeAll> getTypes(List<PetType> types) {
        List <PetTypeAll> typesDto = new ArrayList<>();

        types.forEach(typeEntity->{
            PetTypeAll typeAll = new PetTypeAll();
            typeAll.setId(typeEntity.getId());
            typeAll.setName(typeEntity.getName());
            typesDto.add(typeAll);
        });
        return  typesDto;
    }

    @Override
    public PetType saveType(PetTypeDtoName type) {
        return null;
    }

    @Override
    public PetTypeDtoName getType(PetType type) {

        PetTypeDtoName typeDto = new PetTypeDtoName("");
        typeDto.setName(type.getName());

        return typeDto;
    }

    @Override
    public PetType getTypeId(PetTypeUseId type) {
        
        PetType typeEntity = new PetType();

        typeEntity.setId(type.getId());

        return typeEntity;
    
    }

    @Override
    public Person savePerson(PersoPostnDto personDto) {

        Person person = new Person();
        person.setFirtsName(personDto.getFirtsName());
        person.setSecondName(personDto.getSecondName()!=null? person.getSecondName() : "");
        person.setEmail(personDto.getEmail().toLowerCase());
        person.setPhone(personDto.getPhone());
        person.setLastName(personDto.getLastName());
        person.setPassword(personDto.getPassword());
        return  person;

    }


    @Override
    public PersonGetPostSImple getPerson(Person person){
        PersonGetPostSImple personDto = new PersonGetPostSImple();
        personDto.setId(person.getId());
        personDto.setFirtsName(person.getFirtsName());
        personDto.setSecondName(person.getSecondName());
        personDto.setLastName(person.getLastName());
        personDto.setEmail(person.getEmail());
        personDto.setPhone(person.getPhone());
        personDto.setPassword(person.getPassword());
        List<String> roles = new ArrayList<>();
        person.getRoles().forEach(listRoles->{

            roles.add(listRoles.getRole().getName());


        });

        personDto.setRoles(roles);

        return personDto;
    }

    @Override
    public List<PersonGetPostSImple> getPersons(List <Person> person) {
        List<PersonGetPostSImple> persons = new ArrayList<>();

        person.forEach(p->{

            persons.add(getPerson(p));
        });

        return persons;
    }

    @Override
    public PersonGetId getIdPerson(Person person){
        PersonGetId personGetId = new PersonGetId();
        personGetId.setId(person.getId());
        List <String> roles = new ArrayList<>();
        person.getRoles().forEach(rol->{

        });
        personGetId.setRole(roles);

        return  personGetId;
    }

    @Override
    public List<ApplicationsGetDto> getAplications(List <Applications> applications) {

        List<ApplicationsGetDto> apps= new ArrayList<>();

           applications.forEach(app->{
               apps.add(getAplication(app));
           });
        return apps;
    }




    @Override
    public ApplicationsGetDto getAplication(Applications applications) {
        ApplicationsGetDto applicationsGetDto = new ApplicationsGetDto();
        applicationsGetDto.setId(applications.getId());
        applicationsGetDto.setPerson(getPerson(applications.getPerson()));
        applicationsGetDto.setPet(getPet(applications.getPet()));
        applicationsGetDto.setAddress(applications.getAddress());
        applicationsGetDto.setAlternativePhone(applications.getAlternativePhone());
        applicationsGetDto.setStatus(applications.getStatus());
        applicationsGetDto.setAdoptionMotivation(applications.getAdoptionMotivation());
        applicationsGetDto.setDocument(applications.getDocument());
        return  applicationsGetDto;

    }



    @Override
    public Applications saveAplication(ApplicationsPostDto application){
            Person person = new Person();
            person.setId(application.getPerson().getId());

            Pet pet = new Pet();
            pet.setId(application.getPet().getId());

        System.out.println(person);

        Applications applicationEntity = new Applications();
        applicationEntity.setStatus(application.getStatus());
        applicationEntity.setDocument(application.getDocument());
        applicationEntity.setAlternativePhone(application.getAlternativePhone());
        applicationEntity.setAdoptionMotivation(application.getAdoptionMotivation());
        applicationEntity.setPerson(person);
        applicationEntity.setPet(pet);
        applicationEntity.setAddress(application.getAddress());

        return applicationEntity;

    }




}
