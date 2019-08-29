package onet.grupa.isrentalapplication.service;

import java.util.List;

public interface ISearching<T> {

    List<T> getWithSearchingAndOrder(String searchPhrase, String orderBy);
}
