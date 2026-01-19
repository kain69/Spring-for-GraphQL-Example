package ru.karmazin.graphql.dto;

import java.util.UUID;

public record CommentDto(UUID id, String text) {}