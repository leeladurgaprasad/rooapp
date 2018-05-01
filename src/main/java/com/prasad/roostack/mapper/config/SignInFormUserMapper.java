package com.prasad.roostack.mapper.config;

import org.springframework.stereotype.Component;



import com.prasad.roostack.bean.User;
import com.prasad.roostack.bean.form.SignInForm;
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
