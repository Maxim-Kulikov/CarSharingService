package org.example.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.roleDTO.RoleResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserExisted {
    private Long id;
    private String login;
    private String role;
}
