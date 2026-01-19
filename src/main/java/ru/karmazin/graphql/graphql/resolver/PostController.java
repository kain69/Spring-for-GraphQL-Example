package ru.karmazin.graphql.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.graphql.dto.CommentDto;
import ru.karmazin.graphql.graphql.dto.PostDto;
import ru.karmazin.graphql.graphql.dto.UserDto;
import ru.karmazin.graphql.graphql.dto.input.CreatePostInput;
import ru.karmazin.graphql.service.PostService;
import ru.karmazin.graphql.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @MutationMapping
    public UserDto createPost(@Argument CreatePostInput input) {
        postService.createPost(input);
        return userService.getUser(input.userId());
    }

    @BatchMapping(typeName = "PostDto", field = "comments")
    public Map<PostDto, List<CommentDto>> batchComments(List<PostDto> posts) {
        return postService.getCommentsForPosts(posts);
    }
}
