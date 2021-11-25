package test.repairservice.repairservicev2.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;

    @Column()
    private String password;

    @Column()
    private String fullName;

    @Column()
    private String phoneNumber;

    @ManyToOne()
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "master_device",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = {@JoinColumn(name = "device_id")})
    @Column(columnDefinition = "int default null")
    public Set<Device> masterDevices;
}
