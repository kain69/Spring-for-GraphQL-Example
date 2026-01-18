package ru.karmazin.graphql.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.entity.Comment;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.repository.CommentRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final CommentRepository commentRepository;

    @SchemaMapping(typeName = "Post", field = "comments")
    public List<Comment> comments(Post post) {
        return commentRepository.findByPostId(post.getId());
    }
}
