package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.AddUserForm;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import org.springframework.stereotype.Component;

@Component
public class AddUserFormUserMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory mapperFactory) {
		mapperFactory.classMap(AddUserForm.class, User.class)
						.byDefault()
						.toClassMap();
	}

}
