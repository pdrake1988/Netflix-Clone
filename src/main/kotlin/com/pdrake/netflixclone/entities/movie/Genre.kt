package com.pdrake.netflixclone.entities.movie

import com.pdrake.netflixclone.entities.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "genre")
class Genre(
    @Column(name = "name", unique = true, length = 20, nullable = false)
    val name: String
) : BaseEntity() {
    @ManyToMany(mappedBy = "genres")
    val movies = listOf<Movie>()
}
