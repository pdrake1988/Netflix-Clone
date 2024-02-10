package com.pdrake.netflixclone.config

import org.bouncycastle.util.io.pem.PemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.InputStreamReader
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

@Configuration
class RsaKeyProperties {
    @Bean
    fun rsaPublicKey(): RSAPublicKey {
        val factory = KeyFactory.getInstance("RSA")
        val keyStream = ClassPathResource("certs/public.pem")
        val pemReader = PemReader(InputStreamReader(keyStream.inputStream))
        val pemObject = pemReader.readPemObject()
        val content = pemObject.content
        val pubKeySpec = X509EncodedKeySpec(content)
        return factory.generatePublic(pubKeySpec) as RSAPublicKey
    }

    @Bean
    fun readPKCS8PrivateKey(): RSAPrivateKey {
        val factory = KeyFactory.getInstance("RSA")
        val keyStream = ClassPathResource("certs/private_pkcs8.pem")
        val pemReader = PemReader(InputStreamReader(keyStream.inputStream))
        val pemObject = pemReader.readPemObject()
        val content = pemObject.content
        val privKeySpec = PKCS8EncodedKeySpec(content)
        return factory.generatePrivate(privKeySpec) as RSAPrivateKey
    }
}
