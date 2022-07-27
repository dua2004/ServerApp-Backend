package rizzu82.com.infraserverinventory.service;

import rizzu82.com.infraserverinventory.model.Person;
import rizzu82.com.infraserverinventory.model.Server;
import java.io.IOException;
import java.util.List;

public interface PersonService {
    Person create(Person person);
    List<Person> list(int limit);
    Person get(Long id);
    Person getByEmail(String emailAddress);
    Person update(Person person);
    Boolean delete(Long id);
}
