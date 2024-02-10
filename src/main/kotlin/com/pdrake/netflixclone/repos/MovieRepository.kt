package com.pdrake.netflixclone.repos

import com.pdrake.netflixclone.dtos.MovieDto
import com.pdrake.netflixclone.entities.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Int> {
    @Query("select m from Movie m, Genre g where g.name in ?1")
    fun findMoviesByGenres(genres: List<String>): List<MovieDto>
    fun findMovieById(id: Int): Movie?
}
