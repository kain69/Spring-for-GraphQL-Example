package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.entity.User;
import ru.karmazin.graphql.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @BatchMapping(typeName = "User", field = "posts")
    public Map<User, List<Post>> batchPosts(List<User> users) {
        return userService.getPostsForUsers(users);
    }
}
