package ru.karmazin.graphql.dto;

import java.util.List;
import java.util.UUID;

public record UserDto(UUID id, String name) {}