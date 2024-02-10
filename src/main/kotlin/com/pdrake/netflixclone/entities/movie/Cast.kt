package com.pdrake.netflixclone.entities.movie

import com.pdrake.netflixclone.entities.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "cast")
class Cast(
    @Column(name = "name", length = 20, nullable = false)
    val name: String,
    @Column(name = "age", nullable = false)
    val age: Int,
) : BaseEntity() {
    @ManyToMany(mappedBy = "cast")
    val cast = listOf<Movie>()

}
