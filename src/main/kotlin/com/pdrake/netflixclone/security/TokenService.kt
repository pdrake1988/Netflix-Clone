package com.pdrake.netflixclone.security

import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService(private val jwtEncoder: JwtEncoder) {
    fun createToken(username: String, role: String): String {
        val claimSet = JwtClaimsSet.builder()
            .subject(username)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(2, ChronoUnit.DAYS))
            .claim("authority", role)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(claimSet)).tokenValue

    }
}
