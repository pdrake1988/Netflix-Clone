package com.pdrake.netflixclone.entities.tvshow

import com.pdrake.netflixclone.entities.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tv_genres")
class TvGenres(
    @Column(name = "name", unique = true, length = 20, nullable = false)
    val name: String,
) : BaseEntity() {
    @ManyToMany(mappedBy = "genres")
    val shows = listOf<TvShow>()
}
