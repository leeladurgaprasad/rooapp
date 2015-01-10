package hob.psd.todo.mapper.config;

import hob.psd.todo.bean.Comment;
import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.AddUserForm;
import hob.psd.todo.dto.CommentDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by lgunti on 007, Dec 07.
 */
@Component
public class CommentDTOMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(CommentDTO.class, Comment.class)
                .byDefault()
                .toClassMap();
    }
}
