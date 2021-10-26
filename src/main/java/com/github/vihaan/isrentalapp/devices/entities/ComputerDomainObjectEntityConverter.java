package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.Computer;
import com.github.vihaan.isrentalapp.util.DomainObjectEntityConverter;
import org.springframework.stereotype.Service;

@Service
public class ComputerDomainObjectEntityConverter implements DomainObjectEntityConverter<ComputerEntity, Computer> {


    @Override
    public ComputerEntity convertToEntity(Computer domainObject) {
        return null;
    }

    @Override
    public Computer convertToDomainObject(ComputerEntity domainObject) {
        return null;
    }
}
