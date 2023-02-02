package org.example.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    private Integer id;
    private String mark;
    private String model;
    private String carNumber;
    private Integer price;
    private String limitations;
    private Integer idImage;
}
