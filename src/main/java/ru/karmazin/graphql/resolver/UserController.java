package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.entity.User;
import ru.karmazin.graphql.repository.PostRepository;
import ru.karmazin.graphql.repository.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @SchemaMapping(typeName = "User", field = "posts")
    public List<Post> posts(User user) {
        return postRepository.findByUserId(user.getId());
    }
}
