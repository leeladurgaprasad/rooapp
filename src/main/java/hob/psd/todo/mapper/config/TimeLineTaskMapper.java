package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.Task;
import hob.psd.todo.bean.TimeLine;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lgunti on 024, Dec 24.
 */
@Component
public class TimeLineTaskMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.registerClassMap(mapperFactory.classMap(Task.class,TimeLine.class)
                .byDefault()
                .toClassMap());
    }
}
