package ru.karmazin.graphql.mapper;

import org.mapstruct.Mapper;
import ru.karmazin.graphql.graphql.dto.PostDto;
import ru.karmazin.graphql.model.Post;

import java.util.List;

@Mapper(
    componentModel = "spring"
)
public interface PostMapper {

    PostDto toDto(Post post);

    List<PostDto> toDtoList(List<Post> posts);
}
