package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.entity.NewEntity;
import org.springframework.lang.NonNull;

public interface Mapper<D, E> {
    D toDto(@NonNull E e);

    E toEntity(@NonNull D d);

    D toBasicDto(@NonNull E e);

}
