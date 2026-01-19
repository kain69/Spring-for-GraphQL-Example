package ru.karmazin.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karmazin.graphql.model.Comment;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByPostId(UUID postId);

    List<Comment> findByPostIdIn(List<UUID> postIds);
    List<Comment> findByPostIdIn(Set<UUID> postIds);
}
