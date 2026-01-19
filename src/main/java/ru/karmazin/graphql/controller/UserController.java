package ru.karmazin.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.dto.PostDto;
import ru.karmazin.graphql.dto.UserDto;
import ru.karmazin.graphql.dto.input.CreateUserInput;
import ru.karmazin.graphql.service.UserService;
import ru.karmazin.graphql.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @QueryMapping
    public List<UserDto> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public UserDto user(@Argument UUID id) {
        return userService.getUser(id);
    }

    @MutationMapping
    public UserDto createUser(@Argument CreateUserInput input) {
        return userService.createUser(input);
    }

    @BatchMapping(typeName = "UserDto", field = "posts")
    public Map<UserDto, List<PostDto>> batchPosts(List<UserDto> users) {
        return userService.getPostsForUsers(users);
    }
}
