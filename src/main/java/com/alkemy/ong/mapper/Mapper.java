package com.alkemy.ong.mapper;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D, E> {

    D toDto(@NonNull E e);

}
