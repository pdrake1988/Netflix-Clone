package com.pdrake.netflixclone.entities.tvshow

import com.pdrake.netflixclone.entities.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tv_show_cast_members")
class TVCast(
    @Column(name = "name", length = 20, nullable = false)
    val name: String,
    @Column(name = "age", nullable = false)
    val age: Int,
) : BaseEntity() {
    @ManyToMany(mappedBy = "cast")
    val shows = listOf<TvShow>()
}
