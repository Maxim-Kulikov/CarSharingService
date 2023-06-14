package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//TODO реализовать фильтр для поиска машин
public class CarFilterReq {
    private ArrayList<Integer> idMarks;
    private ArrayList<Integer> idModels;
    private Integer minPrice;
    private Integer maxPrice;
    private SortField sortField;
    private SortOrder sortOrder;
}
