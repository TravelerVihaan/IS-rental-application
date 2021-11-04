package com.github.vihaan.isrentalapp.util;

public interface DomainObjectMapper<T, S> {

     T convertToEntity(S domainObject);

     S convertToDomainObject(T domainObject);

}
