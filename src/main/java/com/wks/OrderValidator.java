package com.wks;

import com.wks.exceptions.NonSequentialOrderException;
import com.wks.exceptions.NonUniqueOrderException;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class OrderValidator {
    private TreeSet<Integer> usedOrders;

    private OrderValidator() {
        this.usedOrders = new TreeSet<>();
    }

    static OrderValidator newSession() {
        return new OrderValidator();
    }

    void validateOrderIsUnique(int order, String fieldName) {
        if (!usedOrders.add(order)) {
            throw new NonUniqueOrderException(order, fieldName);
        }
    }

    void validateOrdersAreSequential() {
        if (usedOrders.isEmpty()) return;
        List<Integer> usedOrdersList = new ArrayList<>(usedOrders);
        for (int i = 0; i + 1 < usedOrdersList.size(); i++) {
            int current = usedOrdersList.get(i);
            int next = usedOrdersList.get(i + 1);
            if (Math.abs(current - next) > 1) {
                throw new NonSequentialOrderException(current, next);
            }
        }
    }
}
