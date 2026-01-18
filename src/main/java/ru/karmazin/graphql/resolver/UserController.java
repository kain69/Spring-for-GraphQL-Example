package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.entity.User;
import ru.karmazin.graphql.repository.PostRepository;
import ru.karmazin.graphql.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @BatchMapping(typeName = "User", field = "posts")
    public Map<User, List<Post>> batchPosts(List<User> users) {
        List<UUID> userIds = users.stream()
            .map(User::getId)
            .toList();

        List<Post> posts = postRepository.findByUserIdIn(userIds);

        return posts.stream()
            .collect(Collectors.groupingBy(Post::getUser));
    }
}
