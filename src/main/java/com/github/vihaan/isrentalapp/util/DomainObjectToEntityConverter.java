package com.github.vihaan.isrentalapp.util;

public interface DomainObjectToEntityConverter {

    <T, S> T convertToEntity(S domainObject);

    <T, S> T convertToDomainObject(S domainObject);

}
