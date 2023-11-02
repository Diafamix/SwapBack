package com.tfg.swapCatBack.data.providers.mappers;


public interface IMapper<T, R> {

    R mapToDto(T t);

    T mapToEntity(R r);

}
