package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//TODO реализовать фильтр для поиска машин
public class CarFilterDto {
    private ArrayList<String> idMarks;
    private ArrayList<String> idModels;
}
