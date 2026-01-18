package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Comment;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.service.PostService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @BatchMapping(typeName = "Post", field = "comments")
    public Map<Post, List<Comment>> batchComments(List<Post> posts) {
        return postService.getCommentsForPosts(posts);
    }
}
