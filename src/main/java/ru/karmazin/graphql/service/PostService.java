package ru.karmazin.graphql.service;

import ru.karmazin.graphql.dto.CommentDto;
import ru.karmazin.graphql.dto.PostDto;
import ru.karmazin.graphql.dto.input.CreatePostInput;

import java.util.List;
import java.util.Map;

public interface PostService {
    Map<PostDto, List<CommentDto>> getCommentsForPosts(List<PostDto> posts);

    PostDto createPost(CreatePostInput input);
}
