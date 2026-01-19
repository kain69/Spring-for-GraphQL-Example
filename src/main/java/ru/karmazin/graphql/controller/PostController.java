package ru.karmazin.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import ru.karmazin.graphql.dto.CommentDto;
import ru.karmazin.graphql.dto.PostDto;
import ru.karmazin.graphql.service.PostService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @BatchMapping(typeName = "PostDto", field = "comments")
    public Map<PostDto, List<CommentDto>> batchComments(List<PostDto> posts) {
        return postService.getCommentsForPosts(posts);
    }
}
