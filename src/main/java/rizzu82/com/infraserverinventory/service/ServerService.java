package rizzu82.com.infraserverinventory.service;

import rizzu82.com.infraserverinventory.model.Server;

import java.util.Collection;
//https://www.youtube.com/watch?v=8ZPsZBcue50
public interface ServerService {
    Server create(Server server);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
