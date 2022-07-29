package rizzu82.com.infraserverinventory.service;

import rizzu82.com.infraserverinventory.model.ServerAdmin;
import rizzu82.com.infraserverinventory.model.ServerAdminId;

import java.io.IOException;
import java.util.List;

public interface ServerAdminService {
    ServerAdmin create(ServerAdmin serverAdmin);
    List<ServerAdmin> list(int limit);
    ServerAdmin get(ServerAdminId serverAdminId);
    ServerAdmin update(ServerAdmin serverAdmin);
    Boolean delete(ServerAdminId serverAdminId);

}