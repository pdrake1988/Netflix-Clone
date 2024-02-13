package com.pdrake.netflixclone.unit.controller

import com.pdrake.netflixclone.controllers.MovieController
import com.pdrake.netflixclone.entities.account.Account
import com.pdrake.netflixclone.repos.AccountRepository
import com.pdrake.netflixclone.repos.MovieRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ExtendWith(MockitoExtension::class)
@WebMvcTest(controllers = [MovieController::class])
class MovieControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var movieController: MovieController

    @MockBean
    private lateinit var accountRepository: AccountRepository

    @MockBean
    private lateinit var movieRepository: MovieRepository


    @Test
    fun `does return list of movies`() {
        mockMvc.get("/movies/fetch-all") {
            with(
                SecurityMockMvcRequestPostProcessors.user("donna").password("Rewind0614")
                    .authorities(SimpleGrantedAuthority("ROLE_USER"))
            )
        }.andExpectAll {
            status {
                isOk()
            }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }
        Mockito.verify(movieRepository).findAll()
    }

    @Test
    fun `does return movie queue`() {
        Mockito.`when`(accountRepository.findAccountByUsername("donna")).thenReturn(Account("donna", "dell1150"))
        mockMvc.get("/movies/fetch-queue") {
            with(
                SecurityMockMvcRequestPostProcessors.user("donna").password("dell1150")
                    .authorities(SimpleGrantedAuthority("ROLE_USER"))
            )
        }.andExpectAll {
            status {
                isOk()
            }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }
        Mockito.verify(accountRepository).findAccountByUsername("donna")
    }
}
