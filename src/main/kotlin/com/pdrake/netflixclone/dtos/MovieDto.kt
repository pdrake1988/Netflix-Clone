package com.pdrake.netflixclone.dtos

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

data class MovieDto(
    @NotBlank(message = "Name is required")
    val name: String,
    @NotBlank(message = "Description is required")
    val description: String,
    @NotBlank(message = "Genres is required")
    val genres: List<String>,
    @Valid
    val cast: List<CastDto>
) {
    constructor(id: Int?, name: String, description: String, genres: List<String>, cast: List<CastDto>) : this(
        name,
        description,
        genres,
        cast
    )
}
