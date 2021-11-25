package test.repairservice.repairservicev2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterAssignRequest {
    private Long requestId;
    private String assigneeUsername;
}
