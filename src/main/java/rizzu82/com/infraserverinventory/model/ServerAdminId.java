package rizzu82.com.infraserverinventory.model;

import java.io.Serializable;
import java.util.Objects;

public class ServerAdminId implements Serializable {

    private Long personid;
    private Long serverid;

    public ServerAdminId() {
    }

    public ServerAdminId(Long personid, Long serverid) {
        this.personid = personid;
        this.serverid = serverid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerAdminId serverAdminId = (ServerAdminId) o;
        return personid.equals(serverAdminId.personid) && serverid.equals(serverAdminId.serverid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personid, serverid);
    }
}