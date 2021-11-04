package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.DiskType;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DiskTypeMapper implements DomainObjectMapper<DiskTypeEntity, DiskType> {
    @Override
    public DiskTypeEntity convertToEntity(DiskType diskType) {
        return null;
    }

    @Override
    public DiskType convertToDomainObject(DiskTypeEntity diskTypeEntity) {
        return DiskType.createFromString(diskTypeEntity.getDiskType());
    }
}
