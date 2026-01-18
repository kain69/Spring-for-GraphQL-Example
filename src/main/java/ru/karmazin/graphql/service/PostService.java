package ru.karmazin.graphql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karmazin.graphql.entity.Comment;
import ru.karmazin.graphql.entity.Post;
import ru.karmazin.graphql.repository.CommentRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final CommentRepository commentRepository;

    public Map<Post, List<Comment>> getCommentsForPosts(List<Post> posts) {
        List<UUID> postIds = posts.stream()
            .map(Post::getId)
            .toList();

        List<Comment> comments = commentRepository.findByPostIdIn(postIds);

        return comments.stream()
            .collect(Collectors.groupingBy(Comment::getPost));
    }
}
