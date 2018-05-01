package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.TodoFile;
import com.prasad.roostack.dto.TodoFileDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lgunti on 008, Dec 08.
 */
@Component
public class TodoFileDTOMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.registerClassMap(mapperFactory.classMap(TodoFile.class, TodoFileDTO.class)
                .byDefault()
                .toClassMap());
    }
}
