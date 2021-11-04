package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerStatus;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ComputerStatusMapper implements DomainObjectMapper<ComputerStatusEntity, ComputerStatus> {

    @Override
    public ComputerStatusEntity convertToEntity(ComputerStatus computerStatus) {
        return null;
    }

    @Override
    public ComputerStatus convertToDomainObject(ComputerStatusEntity computerStatusEntity) {
        return ComputerStatus.createFromString(computerStatusEntity.getStatus());
    }
}
