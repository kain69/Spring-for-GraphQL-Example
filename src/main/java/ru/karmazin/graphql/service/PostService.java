package ru.karmazin.graphql.service;

import ru.karmazin.graphql.dto.CommentDto;
import ru.karmazin.graphql.dto.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    Map<PostDto, List<CommentDto>> getCommentsForPosts(List<PostDto> posts);
}
