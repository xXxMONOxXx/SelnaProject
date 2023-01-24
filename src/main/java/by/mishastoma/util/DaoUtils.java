package by.mishastoma.util;

import by.mishastoma.entity.AbstractEntity;

import java.util.List;

public class DaoUtils {

    private DaoUtils() {

    }

    public static <T extends AbstractEntity> int getIndexOfEntity(T obj, List<T> list) {
        int index = 0;
        for (T it : list) {
            if (obj.getId().equals(it.getId()))
                return index;
            index++;
        }
        return -1;
    }
}
