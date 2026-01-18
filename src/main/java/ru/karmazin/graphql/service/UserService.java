package ru.karmazin.graphql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.entity.User;
import ru.karmazin.graphql.repository.PostRepository;
import ru.karmazin.graphql.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<User, List<Post>> getPostsForUsers(List<User> users) {
        List<UUID> userIds = users.stream()
            .map(User::getId)
            .toList();

        List<Post> posts = postRepository.findByUserIdIn(userIds);

        return posts.stream()
            .collect(Collectors.groupingBy(Post::getUser));
    }
}
