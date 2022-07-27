package rizzu82.com.infraserverinventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rizzu82.com.infraserverinventory.model.Person;
import rizzu82.com.infraserverinventory.model.Server;

public interface PersonRepo extends JpaRepository<Person,Long> {
    Person findByEmailAddress(String emailAddress);
}
