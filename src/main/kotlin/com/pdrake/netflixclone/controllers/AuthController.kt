package com.pdrake.netflixclone.controllers

import com.pdrake.netflixclone.security.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val tokenService: TokenService) {
    @PostMapping("/admin")
    fun loginAdmin(): ResponseEntity<String> {
        return ResponseEntity(tokenService.createToken("pdrake", "ROLE_ADMIN"), HttpStatus.OK)
    }

    @PostMapping("/user")
    fun userLogin(): ResponseEntity<String> {
        return ResponseEntity(tokenService.createToken("donna", "ROLE_USER"), HttpStatus.OK)
    }
}
