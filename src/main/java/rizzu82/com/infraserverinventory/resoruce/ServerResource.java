package rizzu82.com.infraserverinventory.resoruce;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rizzu82.com.infraserverinventory.enumeration.Status;
import rizzu82.com.infraserverinventory.model.Response;
import rizzu82.com.infraserverinventory.model.Server;
import rizzu82.com.infraserverinventory.service.ServerService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/server")
public class ServerResource {
    private final ServerService serverService;
    @Value("${app.imageBasePath}")
    private String imageBasePath;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers",serverService.list(30)))
                        .message("Servers retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server",server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server)  {
        boolean IsServerExist = serverService.findServerByIPAddress(server.getIpAddress());

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server",!IsServerExist ? serverService.create(server) : server))
                        .message(!IsServerExist ? "server is added successfully" : "Server with ipAddress is already exists")
                        .status(!IsServerExist ? CREATED : BAD_REQUEST)
                        .statusCode(!IsServerExist ? CREATED.value() : BAD_REQUEST.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server",serverService.get(id)))
                        .message("Server Retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted",serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}",produces = IMAGE_JPEG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        var path = Paths.get(imageBasePath + "/" + fileName);
        return Files.readAllBytes(path);
    }
}
