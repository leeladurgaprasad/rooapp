package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.Task;
import com.prasad.roostack.dto.TaskDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lgunti on 016, Nov 16.
 */
@Component
public class TaskDTOMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.registerClassMap(mapperFactory.classMap(Task.class,TaskDTO.class)
                .byDefault()
                .toClassMap());
    }
}
