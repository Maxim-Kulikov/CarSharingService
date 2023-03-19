package org.example.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//TODO убрать id и добавить отдельным параметром в контроллер
public class UserUpdateReq {
    //private Long id;
    private String login;
    private String password;
}
