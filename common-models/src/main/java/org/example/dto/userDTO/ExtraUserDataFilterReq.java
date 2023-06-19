package org.example.dto.userDTO;

import lombok.*;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ExtraUserDataFilterReq {
    private List<String> names;
    private List<String> lastnames;
    private String sortField;
    private String sortOrder;
}
