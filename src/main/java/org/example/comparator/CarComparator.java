package org.example.comparator;

import lombok.AllArgsConstructor;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.model.Car;

import java.util.Comparator;

@AllArgsConstructor
public class CarComparator implements Comparator<Car> {
    private SortField sortField;
    private SortOrder sortOrder;

    @Override
    public int compare(Car o1, Car o2) {
        if (sortField == SortField.mark) {
            if (sortOrder == SortOrder.asc) {
                return compareMarks(o1, o2);
            }
            return compareMarks(o2, o1);
        }

        if (sortField == SortField.model) {
            if (sortOrder == SortOrder.asc) {
                return compareModels(o1, o2);
            }
            return compareModels(o2, o1);
        }

        if (sortOrder == SortOrder.asc) {
            return comparePrices(o1, o2);
        }
        return comparePrices(o2, o1);
    }

    private int compareMarks(Car o1, Car o2) {
        return o1.getCarModel().getMark().getMark().compareTo(o2.getCarModel().getMark().getMark());
    }

    private int compareModels(Car o1, Car o2) {
        return o1.getCarModel().getModel().compareTo(o2.getCarModel().getModel());
    }

    private int comparePrices(Car o1, Car o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
