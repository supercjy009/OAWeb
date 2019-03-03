package demo.util;

import demo.model.OrderEntity;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtil {
    public static List<String> compareEntity(Object entity1, Object entity2) throws IllegalAccessException {
        List<String> recordList = new ArrayList<>();
        Field[] fields = entity1.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object o1 = field.get(entity1);
            Object o2 = field.get(entity2);
            //覆盖o1 为 null
            if (o1 == null && o2 == null) {
                continue;
            }
            if (o1 == null && o1 != o2) {
                recordList.add(field.getName());
                continue;
            }

            if (o2 instanceof BigDecimal) {
                if (((BigDecimal) o2).compareTo((BigDecimal) o1) != 0) {
                    recordList.add(field.getName());
                }
            } else if (o2 != null && !o2.equals(o1)) {
                recordList.add(field.getName());
            }
        }
        return recordList;
    }

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(55);
        BigDecimal b2 = new BigDecimal(55);
        Object a = null;
        System.out.println(a instanceof String);
        System.out.println(b1.compareTo(b2));
//        OrderEntity entity1 = new OrderEntity();
//        entity1.setPayProgress("aaaaa1");
//        entity1.setOrderPrice(BigDecimal.valueOf(15.01));
//        OrderEntity entity2 = new OrderEntity();
//        entity2.setPayProgress("aaaaa");
//        entity2.setOrderPrice(BigDecimal.valueOf(15.020));
//        try {
//            System.out.println(EntityUtil.compareEntity(entity1, entity2));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
