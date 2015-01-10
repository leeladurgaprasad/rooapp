package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.Task;
import hob.psd.todo.bean.User;
import hob.psd.todo.dto.TaskDTO;
import hob.psd.todo.dto.UserDTO;
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
