package com.senla.steshko.dto.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AbstractDto {
    private Long id;
    public AbstractDto(Long id) {
        this.id = id;
    }
    public AbstractDto() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDto that = (AbstractDto) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
