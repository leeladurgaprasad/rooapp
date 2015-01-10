package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.form.Tag;
import hob.psd.todo.dto.TagDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * Created by lgunti on 003, Jan 03.
 */
public class TagDTOMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(TagDTO.class, Tag.class)
                .byDefault()
                .toClassMap();
    }
}
