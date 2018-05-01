package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.Tag;
import com.prasad.roostack.dto.TagDTO;
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
