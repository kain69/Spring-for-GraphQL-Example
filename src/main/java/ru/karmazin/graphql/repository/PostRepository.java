package ru.karmazin.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karmazin.graphql.model.Post;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);

    List<Post> findByUserIdIn(List<UUID> userIds);
    List<Post> findByUserIdIn(Set<UUID> userIds);
}
