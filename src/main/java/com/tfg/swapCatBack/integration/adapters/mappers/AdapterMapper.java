package com.tfg.swapCatBack.integration.adapters.mappers;

import java.util.List;

public interface AdapterMapper<T> {

    T mapToDto(String s);

    List<T> mapManyToDto(String s);

}
