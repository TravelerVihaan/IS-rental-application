package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerProducer;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ComputerProducerMapper implements DomainObjectMapper<ComputerProducerEntity, ComputerProducer> {
    @Override
    public ComputerProducerEntity convertToEntity(ComputerProducer computerProducer) {
        return null;
    }

    @Override
    public ComputerProducer convertToDomainObject(ComputerProducerEntity computerProducerEntity) {
        return null;
    }
}
