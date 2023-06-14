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
//TODO убрать idCar и idUser, сделать как отдельный параметр в контроллере
public class OrderCreateReq {
    private Date startDate;
    private Date finishDate;
    private Long idUser;
    private Integer idCar;
}
