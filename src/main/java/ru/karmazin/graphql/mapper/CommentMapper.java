package ru.karmazin.graphql.mapper;

import org.mapstruct.Mapper;
import ru.karmazin.graphql.dto.CommentDto;
import ru.karmazin.graphql.model.Comment;

import java.util.List;

@Mapper(
    componentModel = "spring"
)
public interface CommentMapper {

    CommentDto toDto(Comment post);

    List<CommentDto> toDtoList(List<Comment> posts);
}
