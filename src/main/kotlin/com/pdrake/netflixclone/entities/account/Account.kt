package com.pdrake.netflixclone.entities.account

import com.pdrake.netflixclone.entities.BaseEntity
import com.pdrake.netflixclone.entities.movie.Movie
import com.pdrake.netflixclone.entities.tvshow.TvShow
import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(
    @Column(name = "username", length = 20, nullable = false)
    val username: String,
    @Column(name = "password", length = 255, nullable = false)
    val password: String,
) : BaseEntity() {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    val roles = listOf<Role>()

    @ManyToMany
    @JoinTable(
        name = "movie_queue",
        joinColumns = [JoinColumn(name = "account_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val movieQueue = mutableListOf<Movie>()

    @ManyToMany
    @JoinTable(
        name = "tv_queue",
        joinColumns = [JoinColumn(name = "account_id")],
        inverseJoinColumns = [JoinColumn(name = "tv_show_id")]
    )
    val tvQueue = mutableListOf<TvShow>()
}
