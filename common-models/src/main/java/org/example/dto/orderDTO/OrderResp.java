package org.example.dto.orderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResp {
    private Long id;
    private Date startDate;
    private Date finishDate;
    private Boolean status;
    private Integer idCar;
    private Long idUser;
    private Long idAdmin;
    private String refuseReason;
    private Long price;
}
