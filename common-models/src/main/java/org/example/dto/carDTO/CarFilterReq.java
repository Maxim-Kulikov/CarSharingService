package org.example.dto.carDTO;

import lombok.*;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CarFilterReq {
    private ArrayList<Integer> idMarks;
    private ArrayList<Integer> idModels;
    private Integer minPrice;
    private Integer maxPrice;
    private String sortField;
    private String sortOrder;
}
