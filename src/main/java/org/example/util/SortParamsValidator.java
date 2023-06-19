package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;

public class SortParamsValidator {
    public static Data throwExceptionIfIncorrectInputOrElseGetData(String sortOrderStr, String sortFieldStr) {
        SortOrder sortOrder = null;
        SortField sortField = null;
        if(sortFieldStr != null && sortOrderStr != null && !sortFieldStr.isBlank() && !sortOrderStr.isBlank()) {
            try {
                sortOrder = SortOrder.valueOf(sortOrderStr);
                sortField = SortField.valueOf(sortFieldStr);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Incorrect input sort order or sort field");
            }
        }
        return new Data(sortOrder, sortField);
    }

    @AllArgsConstructor
    @Getter
    public static class Data {
        SortOrder sortOrder;
        SortField sortField;
    }
}
