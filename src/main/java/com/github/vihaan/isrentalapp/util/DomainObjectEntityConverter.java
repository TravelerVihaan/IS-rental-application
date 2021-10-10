package com.github.vihaan.isrentalapp.util;

public interface DomainObjectEntityConverter<T, S> {

     T convertToEntity(S domainObject);

     S convertToDomainObject(T domainObject);

}
