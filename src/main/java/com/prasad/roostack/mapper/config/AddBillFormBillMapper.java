package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.User;
import com.prasad.roostack.bean.form.AddUserForm;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AddBillFormBillMapper extends ConfigurableMapper{
	
	@Override
	protected void configure(MapperFactory mapperFactory) {
		mapperFactory.classMap(AddUserForm.class, User.class)
						.byDefault()
						.toClassMap();
	}

}
