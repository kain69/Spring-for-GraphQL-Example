package ru.karmazin.graphql.graphql.dto.input;

import java.util.UUID;

public record CreatePostInput(String title, UUID userId) {
}
