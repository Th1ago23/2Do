package com.javaproject.webtodo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TaskDTO(@NotBlank String title, @NotBlank String description, @NotNull Date dueDate) {}
