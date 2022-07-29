package rizzu82.com.infraserverinventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="serveradmin")
@IdClass(ServerAdminId.class)
public class ServerAdmin implements Serializable {

    @Id
    @NotEmpty(message = "PersonId cannot be empty or null")
    private Long personid;
    @Id
    @NotEmpty(message = "ServerId cannot be empty or null")
    private Long serverid;
}
