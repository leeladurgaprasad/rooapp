package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.User;
import com.prasad.roostack.dto.UserDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory mapperFactory) {
		mapperFactory.registerClassMap(mapperFactory.classMap(User.class,UserDTO.class)
				  .byDefault()
				  .toClassMap());
	}
	
}
