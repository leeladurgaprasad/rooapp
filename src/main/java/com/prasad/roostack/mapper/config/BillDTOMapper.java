package com.prasad.roostack.mapper.config;

import com.prasad.roostack.bean.Bill;
import com.prasad.roostack.bean.Task;
import com.prasad.roostack.bean.User;
import com.prasad.roostack.dto.BillDTO;
import com.prasad.roostack.dto.TaskDTO;
import com.prasad.roostack.dto.UserDTO;
import com.prasad.roostack.mapper.converter.SecureUserConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class BillDTOMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory mapperFactory) {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter("secureUserConverter", new SecureUserConverter());
        mapperFactory.registerClassMap(mapperFactory.classMap(Bill.class, BillDTO.class)
                .fieldMap("billOwner","billOwner").converter("secureUserConverter").add()
                .byDefault()
                .toClassMap());
    }
}
