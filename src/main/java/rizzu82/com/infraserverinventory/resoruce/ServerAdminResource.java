package rizzu82.com.infraserverinventory.resoruce;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rizzu82.com.infraserverinventory.model.ServerAdmin;
import rizzu82.com.infraserverinventory.service.ServerAdminService;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/server-admin")
public class ServerAdminResource {

    private final ServerAdminService serverAdminService;

    private String imageBasePath;
    @GetMapping("/list")
    public List<ServerAdmin> getServerAdmins(){
        return serverAdminService.list(30);
    }

    @PostMapping("/save")
    public ServerAdmin saveServer(@RequestBody @Valid ServerAdmin serverAdmin)  {
        return serverAdminService.create(serverAdmin);
    }

    @PutMapping("/update")
    public ResponseEntity<ServerAdmin> updateServer(@RequestBody @Valid ServerAdmin serverAdmin)  {
        return ResponseEntity.ok(serverAdminService.update(serverAdmin));
    }
}
