package com.pdrake.netflixclone.entities.tvshow

import com.pdrake.netflixclone.entities.BaseEntity
import com.pdrake.netflixclone.entities.account.Account
import jakarta.persistence.*

@Entity
@Table(name = "tv_show")
class TvShow(
    @Column(name = "name", unique = true, length = 20, nullable = false)
    val name: String,
    @Column(name = "description", length = 255, nullable = false)
    val description: String,
    @ManyToMany
    @JoinTable(
        name = "tv_show_genres",
        joinColumns = [JoinColumn(name = "tv_show_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    val genres: List<TvGenres>,
    @ManyToMany
    @JoinTable(
        name = "tv_show_cast",
        joinColumns = [JoinColumn(name = "tv_show_id")],
        inverseJoinColumns = [JoinColumn(name = "cast_id")]
    )
    val cast: List<TVCast>
) : BaseEntity() {
    @ManyToMany(mappedBy = "tvQueue")
    val tvQueue = listOf<Account>()
}
