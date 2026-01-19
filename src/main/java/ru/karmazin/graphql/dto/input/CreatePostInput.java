package ru.karmazin.graphql.dto.input;

import java.util.UUID;

public record CreatePostInput(String title, UUID userId) {
}
