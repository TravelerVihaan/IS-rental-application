package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerModel;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ComputerModelMapper implements DomainObjectMapper<ComputerModelEntity, ComputerModel> {
    @Override
    public ComputerModelEntity convertToEntity(ComputerModel computerModel) {
        return null;
    }

    @Override
    public ComputerModel convertToDomainObject(ComputerModelEntity computerModelEntity) {
        return null;
    }
}
