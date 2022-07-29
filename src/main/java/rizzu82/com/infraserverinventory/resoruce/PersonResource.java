package rizzu82.com.infraserverinventory.resoruce;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rizzu82.com.infraserverinventory.model.Person;
import rizzu82.com.infraserverinventory.service.PersonService;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonResource {

    private final PersonService personService;
    @Value("${app.imageBasePath}")
    private String imageBasePath;
    @GetMapping("/list")
    public List<Person> getPersons(){
        return personService.list(30);
    }

    @PostMapping("/save")
    public Person savePerson(@RequestBody @Valid Person person)  {
        return personService.create(person);
    }
    @GetMapping("/get/{id}")
    public Person getPerson(@PathVariable("id") @Valid Long id)   {
        return personService.get(id);
    }

    @GetMapping("/getPersonByEmail/{emailAddress}")
    public Person getServer(@PathVariable("emailAddress") @Valid String emailAddress)   {
        return personService.getByEmail(emailAddress);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePerson(@PathVariable("id") Long id)  {
        return personService.delete(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person)  {
        var data = personService.update(person);
        return ResponseEntity.ok(data);
    }
    @GetMapping(path = "/image/{fileName}",produces = IMAGE_JPEG_VALUE)
    public byte[] gerPersonProfileImage(@PathVariable("fileName") String fileName) throws IOException {
        var path = Paths.get(imageBasePath + "/" + fileName);
        return Files.readAllBytes(path);
    }

}
