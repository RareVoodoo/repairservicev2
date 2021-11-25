package test.repairservice.repairservicev2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String oldUsername;
    private List<String> devices;
}
