package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.ComputerModel;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerModelMapper implements DomainObjectMapper<ComputerModelEntity, ComputerModel> {

    private final ComputerProducerMapper computerProducerMapper;

    @Autowired
    public ComputerModelMapper(ComputerProducerMapper computerProducerMapper) {
        this.computerProducerMapper = computerProducerMapper;
    }

    @Override
    public ComputerModelEntity convertToEntity(ComputerModel computerModel) {
        return new ComputerModelEntity(computerModel.getModelName(),
                computerProducerMapper.convertToEntity(computerModel.getComputerProducer()));
    }

    public List<ComputerModelEntity> convertCollectionToEntities(Collection<ComputerModel> computerModels) {
        return computerModels.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<ComputerModel> convertCollectionToDomainObjects(Collection<ComputerModelEntity> computerModelEntities) {
        return computerModelEntities.stream()
                .map(this::convertToDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public ComputerModel convertToDomainObject(ComputerModelEntity computerModelEntity) {
        return new ComputerModel(computerModelEntity.getModelName(),
                computerProducerMapper.convertToDomainObject(computerModelEntity.getComputerProducer()));
    }
}
