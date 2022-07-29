package rizzu82.com.infraserverinventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rizzu82.com.infraserverinventory.model.ServerAdmin;
import rizzu82.com.infraserverinventory.model.ServerAdminId;

public interface ServerAdminRepo extends JpaRepository<ServerAdmin, ServerAdminId> {

}
