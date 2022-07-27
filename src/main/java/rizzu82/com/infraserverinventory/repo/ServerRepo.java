package rizzu82.com.infraserverinventory.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import rizzu82.com.infraserverinventory.model.Server;

public interface ServerRepo extends JpaRepository<Server,Long> {
    Server findByIpAddress(String ipAddress);
}
