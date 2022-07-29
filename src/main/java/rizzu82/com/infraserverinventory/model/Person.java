package rizzu82.com.infraserverinventory.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Person name cannot be empty or null")
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "Person email cannot be empty or null")
    private String emailAddress;
    private String profilePicture;


}
