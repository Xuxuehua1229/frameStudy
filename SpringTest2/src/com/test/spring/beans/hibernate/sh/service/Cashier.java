package com.test.spring.beans.hibernate.sh.service;

import java.util.List;

public interface Cashier {
    public void checkout(String username,List<Integer> isbn);
}
