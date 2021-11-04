package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerModel;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerModelMapper implements DomainObjectMapper<ComputerModelEntity, ComputerModel> {

    private final ComputerProducerMapper computerProducerMapper;

    @Autowired
    public ComputerModelMapper(ComputerProducerMapper computerProducerMapper) {
        this.computerProducerMapper = computerProducerMapper;
    }

    @Override
    public ComputerModelEntity convertToEntity(ComputerModel computerModel) {
        return null;
    }

    @Override
    public ComputerModel convertToDomainObject(ComputerModelEntity computerModelEntity) {
        return new ComputerModel(computerModelEntity.getModelName(),
                computerProducerMapper.convertToDomainObject(computerModelEntity.getComputerProducer()));
    }
}
