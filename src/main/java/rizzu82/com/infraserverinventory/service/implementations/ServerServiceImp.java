package rizzu82.com.infraserverinventory.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import rizzu82.com.infraserverinventory.enumeration.Status;
import rizzu82.com.infraserverinventory.model.Server;
import rizzu82.com.infraserverinventory.repo.ServerRepo;
import rizzu82.com.infraserverinventory.service.ServerService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService {

    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        log.info("Saving a new server : {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server IP: {}",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(1000)? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("find all server limit : {}",limit);
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("find server by id : {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("updating server : {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting server by ID : {}",id);
        serverRepo.deleteById(id);
        return true;
    }

    @Override
    public Boolean findServerByIPAddress(String ipAddress) {
        log.info("finding server by IP: {}",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        if(server !=null && server.getIpAddress().equals(ipAddress)) {
            return true;
        }
        else {
            return false;
        }
    }

    private String setServerImageUrl() {
        String[] imageName = {"server1.jpg","server2.jpg","server3.jpg","server4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+imageName[new Random().nextInt(4)]).toUriString();
    }
}
