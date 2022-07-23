package rizzu82.com.infraserverinventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rizzu82.com.infraserverinventory.enumeration.Status;
import rizzu82.com.infraserverinventory.model.Server;
import rizzu82.com.infraserverinventory.repo.ServerRepo;

@SpringBootApplication
public class InfraServerInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraServerInventoryApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){

		return args -> {
			//serverRepo.save(new Server(null,"192.168.0.1","Windows Server","16 GB Ram","Database Server","http://localhost:8080/server/image/server1.jpg",Status.SERVER_UP));
			//serverRepo.save(new Server(null,"192.168.0.2","Windows Server","16 GB Ram","Web Server","http://localhost:8080/server/image/server2.jpg",Status.SERVER_UP));
			//serverRepo.save(new Server(null,"192.168.0.3","Windows Server","16 GB Ram","Services Server","http://localhost:8080/server/image/server3.jpg",Status.SERVER_UP));
			//serverRepo.save(new Server(null,"192.168.0.4","Windows Server","16 GB Ram","File server","http://localhost:8080/server/image/server4.jpg",Status.SERVER_UP));
		};
	}
	 */
}
