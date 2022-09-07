package com.alkemy.ong.mapper;


import org.springframework.lang.NonNull;

public interface Mapper<D, E> {
    D toDto(@NonNull E e);

    E toEntity(@NonNull D d);

    D toBasicDto(@NonNull E e);

}
