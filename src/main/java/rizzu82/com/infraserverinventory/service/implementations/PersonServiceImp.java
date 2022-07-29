package rizzu82.com.infraserverinventory.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rizzu82.com.infraserverinventory.exception.PersonException;
import rizzu82.com.infraserverinventory.exception.ServerNotFoundException;
import rizzu82.com.infraserverinventory.model.Person;
import rizzu82.com.infraserverinventory.model.Server;
import rizzu82.com.infraserverinventory.repo.PersonRepo;
import rizzu82.com.infraserverinventory.service.PersonService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PersonServiceImp implements PersonService {

    private final PersonRepo personRepo;


    private String setPersonProfileImageUrl() {
        String[] imageName = {"server1.jpg","server2.jpg","server3.jpg","server4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+imageName[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Person create(Person person) {
        log.info("Saving a new server : {}",person.getName());
        person.setProfilePicture(setPersonProfileImageUrl());
        return personRepo.save(person);
    }

    @Override
    public List<Person> list(int limit) {
        log.info("find all server limit : {}",limit);
        return personRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Person get(Long id) {
        log.info("find server by id : {}",id);
        Person person = personRepo.findById(id).orElse(null);
        if(person == null)
        {
            throw new PersonException(id.toString(),"", new NullPointerException());
        }
        return person;
    }

    @Override
    public Person getByEmail(String emailAddress) {
        log.info("find server by email : {}",emailAddress);
        Person person = personRepo.findByEmailAddress(emailAddress);
        if(person == null)
        {
            throw new PersonException(emailAddress,"", new NullPointerException());
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        log.info("updating person : {}",person.getName());

        return personRepo.save(person);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting person by ID : {}",id);
        try {
            personRepo.deleteById(id);
            return true;
        }
        catch (Exception ex)
        {
            throw new PersonException(id.toString(),ex.getMessage(),ex);
        }
    }
}
