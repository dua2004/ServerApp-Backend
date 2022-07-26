package rizzu82.com.infraserverinventory.resoruce;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rizzu82.com.infraserverinventory.enumeration.Status;
import rizzu82.com.infraserverinventory.exception.ServerNotFoundException;
import rizzu82.com.infraserverinventory.model.Response;
import rizzu82.com.infraserverinventory.model.Server;
import rizzu82.com.infraserverinventory.service.ServerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/server")
public class ServerResource {
    private final ServerService serverService;
    @Value("${app.imageBasePath}")
    private String imageBasePath;
    @GetMapping("/list")
    public List<Server> getServers(){
      return serverService.list(30);
    }
    @GetMapping("/ping/{ipAddress}")
    public Server pingServer(@PathVariable("ipAddress") @Valid String ipAddress) throws IOException {
        return serverService.ping(ipAddress);
    }

    @PostMapping("/save")
    public Server saveServer(@RequestBody @Valid Server server)  {
        return serverService.create(server);
    }
    @GetMapping("/get/{id}")
    public Server getServer(@PathVariable("id") @Valid Long id)   {
        return serverService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteServer(@PathVariable("id") Long id)  {
        return serverService.delete(id);
    }
    @PutMapping("/update")
    public ResponseEntity<Server> updateServer(@RequestBody @Valid Server server)  {
        var data = serverService.update(server);
        return ResponseEntity.ok(data);
    }
    @GetMapping(path = "/image/{fileName}",produces = IMAGE_JPEG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        var path = Paths.get(imageBasePath + "/" + fileName);
        return Files.readAllBytes(path);
    }
}
