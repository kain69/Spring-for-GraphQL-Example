package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Comment;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.repository.CommentRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final CommentRepository commentRepository;

    @BatchMapping(typeName = "Post", field = "comments")
    public Map<Post, List<Comment>> batchComments(List<Post> posts) {
        List<UUID> postIds = posts.stream()
            .map(Post::getId)
            .toList();

        List<Comment> comments = commentRepository.findByPostIdIn(postIds);

        return comments.stream()
            .collect(Collectors.groupingBy(Comment::getPost));
    }
}
