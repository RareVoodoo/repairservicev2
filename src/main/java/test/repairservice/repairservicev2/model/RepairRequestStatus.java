package test.repairservice.repairservicev2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.repairservice.repairservicev2.util.RequestStatus;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairRequestStatus {

    public RepairRequestStatus(RequestStatus requestStatus){
        this.id = requestStatus.getId();
        this.title = requestStatus.getTitle();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

}
