package ru.karmazin.graphql.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karmazin.graphql.graphql.dto.PostDto;
import ru.karmazin.graphql.graphql.dto.UserDto;
import ru.karmazin.graphql.graphql.dto.input.CreateUserInput;
import ru.karmazin.graphql.model.Post;
import ru.karmazin.graphql.model.User;
import ru.karmazin.graphql.mapper.PostMapper;
import ru.karmazin.graphql.mapper.UserMapper;
import ru.karmazin.graphql.repository.PostRepository;
import ru.karmazin.graphql.repository.UserRepository;
import ru.karmazin.graphql.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
        return userMapper.toDto(user);
    }

    @Override
    public Map<UserDto, List<PostDto>> getPostsForUsers(List<UserDto> users) {

        Map<UUID, UserDto> usersById = users.stream()
            .collect(Collectors.toMap(UserDto::id, Function.identity()));

        List<Post> posts = postRepository.findByUserIdIn(usersById.keySet());

        Map<UUID, List<PostDto>> postsByUserId = posts.stream()
            .collect(Collectors.groupingBy(
                post -> post.getUser().getId(),
                Collectors.mapping(postMapper::toDto, Collectors.toList())
            ));

        return users.stream()
            .collect(Collectors.toMap(
                Function.identity(),
                user -> postsByUserId.getOrDefault(user.id(), List.of())
            ));
    }

    @Override
    public UserDto createUser(CreateUserInput input) {
        User newUser = new User(input.name());
        return userMapper.toDto(userRepository.save(newUser));
    }
}
