package rizzu82.com.infraserverinventory.service;

import rizzu82.com.infraserverinventory.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;
//https://www.youtube.com/watch?v=8ZPsZBcue50
public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
    Boolean findServerByIPAddress(String ipAddress);

}
