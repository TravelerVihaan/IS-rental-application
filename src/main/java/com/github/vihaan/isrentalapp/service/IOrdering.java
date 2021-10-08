package com.github.vihaan.isrentalapp.service;

import java.util.List;
import java.util.Set;

public interface IOrdering<T> {

    List<T> sortOrderingBy(Set<T> inputSet, String orderBy);
}
