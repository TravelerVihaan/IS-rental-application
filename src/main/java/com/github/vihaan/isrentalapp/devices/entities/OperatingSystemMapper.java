package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.OperatingSystem;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OperatingSystemMapper implements DomainObjectMapper<OperatingSystemEntity, OperatingSystem> {
    @Override
    public OperatingSystemEntity convertToEntity(OperatingSystem operatingSystem) {
        return new OperatingSystemEntity(operatingSystem.getOperatingSystemName());
    }

    @Override
    public OperatingSystem convertToDomainObject(OperatingSystemEntity operatingSystemEntity) {
        return OperatingSystem.createFromString(operatingSystemEntity.getOperatingSystem());
    }
}
