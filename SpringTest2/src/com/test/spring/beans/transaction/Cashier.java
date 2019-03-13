package com.test.spring.beans.transaction;

import java.util.List;

public interface Cashier {
    public void checkout(String username,List<Integer> isbn);
}
