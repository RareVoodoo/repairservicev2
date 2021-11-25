package test.repairservice.repairservicev2.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String summary;
    private String description;

    @Column()
    private Instant creationDate;


    @ManyToOne(cascade = javax.persistence.CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "request_creator_id", columnDefinition = "int default null")
    private User creator;


    @ManyToOne(cascade = javax.persistence.CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "assignee_master_id", columnDefinition = "int default null")
    private User assignee;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private RepairRequestStatus repairRequestStatus;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "device_id")
    private Device device;
}
