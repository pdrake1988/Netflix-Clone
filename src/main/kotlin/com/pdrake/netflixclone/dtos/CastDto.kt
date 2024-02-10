package com.pdrake.netflixclone.dtos

import jakarta.validation.constraints.NotBlank

data class CastDto(
    @NotBlank(message = "Name is required")
    val name: String,
    @NotBlank(message = "Age is required")
    val age: Int
)
