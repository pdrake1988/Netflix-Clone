package com.pdrake.netflixclone.entities.movie

import com.pdrake.netflixclone.entities.BaseEntity
import com.pdrake.netflixclone.entities.account.Account
import jakarta.persistence.*

@Entity
@Table(name = "movie")
class Movie(
    @Column(name = "name", unique = true, length = 20, nullable = false)
    val name: String,
    @Column(name = "description", length = 255, nullable = false)
    val description: String,
    @ManyToMany
    @JoinTable(
        name = "movie_genres",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    val genres: List<Genre>,
    @ManyToMany
    @JoinTable(
        name = "movie_cast",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "cast_id")]
    )
    val cast: List<Cast>
) : BaseEntity() {
    @ManyToMany(mappedBy = "movieQueue")
    val movieQueue = listOf<Account>()

}
