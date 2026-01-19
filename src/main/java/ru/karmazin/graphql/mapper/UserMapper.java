package ru.karmazin.graphql.mapper;

import org.mapstruct.Mapper;
import ru.karmazin.graphql.dto.UserDto;
import ru.karmazin.graphql.model.User;

import java.util.List;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}