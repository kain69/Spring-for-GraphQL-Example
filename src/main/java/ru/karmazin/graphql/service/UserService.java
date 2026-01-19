package ru.karmazin.graphql.service;

import ru.karmazin.graphql.dto.PostDto;
import ru.karmazin.graphql.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUser(UUID id);
    Map<UserDto, List<PostDto>> getPostsForUsers(List<UserDto> users);
}
