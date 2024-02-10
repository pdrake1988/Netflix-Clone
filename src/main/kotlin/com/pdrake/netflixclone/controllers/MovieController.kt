package com.pdrake.netflixclone.controllers

import com.pdrake.netflixclone.dtos.CastDto
import com.pdrake.netflixclone.dtos.MovieDto
import com.pdrake.netflixclone.dtos.QueueDto
import com.pdrake.netflixclone.dtos.TvShowDto
import com.pdrake.netflixclone.entities.movie.Cast
import com.pdrake.netflixclone.entities.movie.Genre
import com.pdrake.netflixclone.entities.movie.Movie
import com.pdrake.netflixclone.repos.AccountRepository
import com.pdrake.netflixclone.repos.MovieRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/movies")
class MovieController(private val movieRepository: MovieRepository, private val accountRepository: AccountRepository) {
    @GetMapping("/fetch-all")
    fun fetchMovies(): List<MovieDto> {
        return movieRepository.findAll().map { movie ->
            MovieDto(movie.id, movie.name, movie.description, movie.genres.map {
                it.name
            }, movie.cast.map {
                CastDto(it.name, it.age)
            })
        }
    }

    @GetMapping("/fetch-queue")
    fun fetchQueue(principal: Principal): QueueDto {
        val account = accountRepository.findAccountByUsername(principal.name)
        if (account != null) {
            return QueueDto(account.movieQueue.map { movie ->
                MovieDto(movie.id, movie.name, movie.description, movie.genres.map {
                    it.name
                }, movie.cast.map {
                    CastDto(it.name, it.age)
                })
            }, account.tvQueue.map { tvShow ->
                TvShowDto(tvShow.name, tvShow.description, tvShow.genres.map {
                    it.name
                })
            })
        }
        throw UsernameNotFoundException("Username not found!")
    }

    @GetMapping("/fetchAllByGenre")
    fun getAllByGenre(@RequestParam genres: List<String>): List<MovieDto> {
        return movieRepository.findMoviesByGenres(genres)
    }

    @PostMapping("/create-movie")
    @PreAuthorize("hasRole('ADMIN')")
    fun createMovie(@Valid @RequestBody movieDto: MovieDto): ResponseEntity<String> {
        val newMovie = Movie(movieDto.name, movieDto.description, movieDto.genres.map {
            Genre(it)
        }, movieDto.cast.map {
            Cast(it.name, it.age)
        })
        movieRepository.save(newMovie)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/add-to-queue/{id}")
    @PreAuthorize("hasRole('USER')")
    fun addToQueue(@PathVariable id: Int, principal: Principal): ResponseEntity<String> {
        val movie = movieRepository.findMovieById(id)
        val account = accountRepository.findAccountByUsername(principal.name)
        if (movie != null && account != null) {
            account.movieQueue.add(movie)
            accountRepository.save(account)
            return ResponseEntity(HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun userNameNotFound(exception: UsernameNotFoundException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequest(ex: MethodArgumentNotValidException): ResponseEntity<List<String?>> {
        val errors = ex.allErrors.map {
            it.defaultMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
