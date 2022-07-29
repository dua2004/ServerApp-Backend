package rizzu82.com.infraserverinventory.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rizzu82.com.infraserverinventory.exception.ServerNotFoundException;
import rizzu82.com.infraserverinventory.model.ServerAdmin;
import rizzu82.com.infraserverinventory.model.ServerAdminId;
import rizzu82.com.infraserverinventory.repo.ServerAdminRepo;
import rizzu82.com.infraserverinventory.service.ServerAdminService;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerAdminServiceImp implements ServerAdminService {
    private final ServerAdminRepo serverAdminRepo;

    @Override
    public ServerAdmin create(ServerAdmin serverAdmin) {
        return serverAdminRepo.save(serverAdmin);
    }

    @Override
    public List<ServerAdmin> list(int limit) {
        return serverAdminRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public ServerAdmin get(ServerAdminId serverAdminId) {
        ServerAdmin serverAdminRepoById = serverAdminRepo.findById(serverAdminId).orElse(null);
        if(serverAdminRepoById == null) {
            throw new ServerNotFoundException(serverAdminId.toString(),"", new NullPointerException());
        }
        return serverAdminRepoById;
    }

    @Override
    public ServerAdmin update(ServerAdmin serverAdmin) {
       return serverAdminRepo.save(serverAdmin);
    }

    @Override
    public Boolean delete(ServerAdminId serverAdminId) {
        try{
            serverAdminRepo.deleteById(serverAdminId);
            return true;
        }
        catch (Exception ex)
        {
            throw new ServerNotFoundException(serverAdminId.toString(),ex.getMessage(),ex);
        }
    }
}
