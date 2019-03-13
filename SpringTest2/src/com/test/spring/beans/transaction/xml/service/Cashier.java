package com.test.spring.beans.transaction.xml.service;

import java.util.List;

public interface Cashier {
    public void checkout(String username,List<Integer> isbn);
}
