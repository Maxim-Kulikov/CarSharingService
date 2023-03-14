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
public class OrderCreationRequest {
    private Date startDate;
    private Date finishDate;
    private Integer idCar;
    private Long idUser;
}
