package com.pdrake.netflixclone.integration.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.pdrake.netflixclone.NetflixCloneApplication
import com.pdrake.netflixclone.dtos.CastDto
import com.pdrake.netflixclone.dtos.MovieDto
import com.pdrake.netflixclone.dtos.QueueDto
import com.pdrake.netflixclone.entities.account.Account
import com.pdrake.netflixclone.entities.movie.Cast
import com.pdrake.netflixclone.entities.movie.Genre
import com.pdrake.netflixclone.entities.movie.Movie
import com.pdrake.netflixclone.repos.AccountRepository
import com.pdrake.netflixclone.repos.MovieRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(MockitoExtension::class)
@SpringBootTest(classes = [NetflixCloneApplication::class])
class MovieControllerIT {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var movieRepository: MovieRepository

    @MockBean
    private lateinit var accountRepository: AccountRepository

    @BeforeEach
    fun setup(context: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity()).build()
    }

    @Test
    fun `does endpoint return list of movies`() {
        val movies =
            mutableListOf(Movie("saw", "torture porn", listOf(Genre("horror")), listOf(Cast("tobin bell", 81))))
        Mockito.`when`(movieRepository.findAll()).thenReturn(movies)
        mockMvc.get("/movies/fetch-all") {
            with(SecurityMockMvcRequestPostProcessors.jwt().jwt { userClaims ->
                userClaims.subject("donna")
                userClaims.claims {
                    it["authority"] = "ROLE_USER"
                }
            })
        }.andExpectAll {
            status {
                isOk()
            }
            content {
                json(objectMapper.writeValueAsString(movies.map { movie ->
                    MovieDto(movie.id, movie.name, movie.description, movie.genres.map {
                        it.name
                    }, movie.cast.map {
                        CastDto(it.name, it.age)
                    })
                }))
            }
        }
        Mockito.verify(movieRepository).findAll()
    }

    @Test
    fun `does return movie queue`() {
        val account = Account("donna", "dell1150")
        Mockito.`when`(accountRepository.findAccountByUsername(account.username)).thenReturn(account)
        val movies = QueueDto(account.movieQueue.map { movie ->
            MovieDto(movie.id, movie.name, movie.description, movie.genres.map {
                it.name
            }, movie.cast.map {
                CastDto(it.name, it.age)
            })
        }, emptyList())
        mockMvc.get("/movies/fetch-queue") {
            with(SecurityMockMvcRequestPostProcessors.jwt().jwt { claims ->
                claims.subject("donna")
                claims.claims {
                    it["authority"] = "ROLE_USER"
                }
            })
        }.andExpectAll {
            status {
                isOk()
            }
            content {
                json(objectMapper.writeValueAsString(movies))
            }
        }
        Mockito.verify(accountRepository).findAccountByUsername("donna")
    }
}
