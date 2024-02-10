package com.pdrake.netflixclone.repos

import com.pdrake.netflixclone.entities.tvshow.TvShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TvShowRepository : JpaRepository<TvShow, Long>
