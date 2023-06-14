package org.example.dto.orderDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderFilterReq {
    private Boolean status;
    private Integer idCar;
    private Long idUser;
    private Long idAdmin;
}
