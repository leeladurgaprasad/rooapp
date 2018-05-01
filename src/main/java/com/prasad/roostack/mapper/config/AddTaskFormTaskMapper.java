package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.Task;
import com.prasad.roostack.bean.form.AddTaskForm;
import com.prasad.roostack.mapper.converter.StringToDateConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lgunti on 016, Nov 16.
 */
@Component
public class AddTaskFormTaskMapper extends ConfigurableMapper {
        @Override
        protected void configure(MapperFactory mapperFactory) {
            ConverterFactory converterFactory = mapperFactory.getConverterFactory();
            converterFactory.registerConverter("dateConverter", new StringToDateConverter("dd-MM-yyyy"));

            mapperFactory.classMap(AddTaskForm.class, Task.class)
                    .fieldMap("taskDeadLineDate","taskDeadLineDate").converter("dateConverter").add().register();
            mapperFactory.classMap(AddTaskForm.class, Task.class)
                    .fieldMap("taskDeadLineDate","taskDeadLineDate").converter("dateConverter").add()
                    .byDefault()
                    .register();
        }
}
