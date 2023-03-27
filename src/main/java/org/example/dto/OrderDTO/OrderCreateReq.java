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
//TODO убрать idCar и idUser, сделать как отдельный параметр в контроллере
public class OrderCreateReq {
    private Date startDate;
    private Date finishDate;
}
