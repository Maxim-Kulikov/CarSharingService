package org.example.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private Date startDate;
    private Date finishDate;
    private Boolean status;
    private Car car;
    private Integer idUser;
    private String adminLogin;
    private String refuseReason;
}
