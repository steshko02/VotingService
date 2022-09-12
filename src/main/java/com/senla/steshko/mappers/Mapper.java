package com.senla.steshko.mappers;

import com.senla.steshko.dto.entities.AbstractDto;
import com.senla.steshko.entities.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends AbstractDto> {
    E toEntity(D dto);
    D toDto(E entity);
}