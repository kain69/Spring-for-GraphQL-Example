package ru.karmazin.graphql.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karmazin.graphql.dto.CommentDto;
import ru.karmazin.graphql.dto.PostDto;
import ru.karmazin.graphql.dto.input.CreatePostInput;
import ru.karmazin.graphql.mapper.PostMapper;
import ru.karmazin.graphql.model.Comment;
import ru.karmazin.graphql.mapper.CommentMapper;
import ru.karmazin.graphql.model.Post;
import ru.karmazin.graphql.repository.CommentRepository;
import ru.karmazin.graphql.repository.PostRepository;
import ru.karmazin.graphql.service.PostService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Override
    public Map<PostDto, List<CommentDto>> getCommentsForPosts(List<PostDto> posts) {

        Map<UUID, PostDto> postsById = posts.stream()
            .collect(Collectors.toMap(PostDto::id, Function.identity()));

        List<Comment> comments =
            commentRepository.findByPostIdIn(postsById.keySet());

        Map<UUID, List<CommentDto>> commentsByPostId = comments.stream()
            .collect(Collectors.groupingBy(
                comment -> comment.getPost().getId(),
                Collectors.mapping(commentMapper::toDto, Collectors.toList())
            ));

        return posts.stream()
            .collect(Collectors.toMap(
                Function.identity(),
                post -> commentsByPostId.getOrDefault(post.id(), List.of())
            ));
    }

    @Override
    public PostDto createPost(CreatePostInput input) {
        Post newPost = new Post(input.title(), input.userId());
        return postMapper.toDto(postRepository.save(newPost));
    }
}
