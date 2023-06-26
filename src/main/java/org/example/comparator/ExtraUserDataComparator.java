package org.example.comparator;

import lombok.AllArgsConstructor;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.model.Car;
import org.example.model.ExtraUserData;

import java.util.Comparator;

@AllArgsConstructor
public class ExtraUserDataComparator implements Comparator<ExtraUserData> {
    private SortField sortField;
    private SortOrder sortOrder;

    @Override
    public int compare(ExtraUserData o1, ExtraUserData o2) {
        if (sortField == SortField.name) {
            if(o1.getName() == null || o2.getName() == null) {
                return 1;
            }
            if (sortOrder == SortOrder.asc) {
                return compareNames(o1, o2);
            }
            return compareNames(o2, o1);
        }

        if(o1.getLastname() == null || o2.getLastname() == null) {
            return 1;
        }
        if (sortOrder == SortOrder.asc) {
            return compareLastnames(o1, o2);
        }
        return compareLastnames(o2, o1);
    }

    private int compareNames(ExtraUserData o1, ExtraUserData o2) {
        return o1.getName().compareTo(o2.getName());
    }

    private int compareLastnames(ExtraUserData o1, ExtraUserData o2) {
        return o1.getLastname().compareTo(o2.getLastname());
    }
}
