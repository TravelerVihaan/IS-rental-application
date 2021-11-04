package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerProducer;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComputerProducerMapper implements DomainObjectMapper<ComputerProducerEntity, ComputerProducer> {

    private final ComputerModelMapper computerModelMapper;

    @Autowired
    public ComputerProducerMapper(ComputerModelMapper computerModelMapper) {
        this.computerModelMapper = computerModelMapper;
    }

    @Override
    public ComputerProducerEntity convertToEntity(ComputerProducer computerProducer) {
        ComputerProducerEntity computerProducerEntity = new ComputerProducerEntity(computerProducer.getProducerName());
        computerProducerEntity.setComputerModels(computerModelMapper.convertCollectionToEntities(computerProducer.getComputerModels()));
        return computerProducerEntity;
    }

    @Override
    public ComputerProducer convertToDomainObject(ComputerProducerEntity computerProducerEntity) {
        return new ComputerProducer(computerProducerEntity.getProducerName(),
                computerProducerEntity.getComputerModels().stream()
                        .map(computerModelMapper::convertToDomainObject).collect(Collectors.toList()));
    }
}
