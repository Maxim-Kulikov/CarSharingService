package org.example.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Date startDate;
    private Date finishDate;
    private Boolean status;
    private Integer idCar;
    private Long idUser;
    private String adminLogin;
    private String refuseReason;
    private Long price;
}
