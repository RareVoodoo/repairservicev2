package test.repairservice.repairservicev2.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairRequestCreationRequest {
    private String summary;
    private String description;
    private String deviceType;
}
