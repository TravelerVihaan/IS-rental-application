package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.rentals.RentStatus;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class RentStatusMapper implements DomainObjectMapper<RentStatusEntity, RentStatus> {

    @Override
    public RentStatusEntity convertToEntity(RentStatus rentStatus) {
        return new RentStatusEntity(rentStatus.getRentStatus());
    }

    @Override
    public RentStatus convertToDomainObject(RentStatusEntity rentStatusEntity) {
        return RentStatus.createFromString(rentStatusEntity.getStatus());
    }
}
