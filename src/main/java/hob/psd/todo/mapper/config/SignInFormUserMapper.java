package hob.psd.todo.mapper.config;

import org.springframework.stereotype.Component;



import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.SignInForm;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class SignInFormUserMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory mapperFactory) {
		mapperFactory.classMap(SignInForm.class, User.class)
						.byDefault()
						.toClassMap();
	}

}
