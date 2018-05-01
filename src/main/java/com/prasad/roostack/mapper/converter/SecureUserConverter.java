package com.prasad.roostack.mapper.converter;

import com.prasad.roostack.bean.User;
import com.prasad.roostack.dto.UserDTO;
import com.prasad.roostack.mapper.config.SecureUserDTOMapper;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lgunti on 001, Dec 01.
 */
@Component
public class SecureUserConverter extends BidirectionalConverter<User,UserDTO> {

    @Override
    public UserDTO convertTo(User source, Type<UserDTO> destinationType) {
        return secureUserDTOMapper.map(source,UserDTO.class);
    }

    @Override
    public User convertFrom(UserDTO source, Type<User> destinationType) {
        return secureUserDTOMapper.map(source,User.class);
    }

    @Autowired
    private static SecureUserDTOMapper secureUserDTOMapper = new SecureUserDTOMapper();

}
