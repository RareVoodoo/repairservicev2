package test.repairservice.repairservicev2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "master_device",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Column(columnDefinition = "int default null")
    private Set<User> deviceUsers;
}
