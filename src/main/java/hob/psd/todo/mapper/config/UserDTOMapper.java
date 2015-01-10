package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.User;
import hob.psd.todo.dto.UserDTO;
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
