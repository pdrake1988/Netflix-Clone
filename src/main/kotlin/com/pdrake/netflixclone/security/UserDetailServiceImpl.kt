package com.pdrake.netflixclone.security

import com.pdrake.netflixclone.repos.AccountRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val account = accountRepository.findAccountByUsername(username)
        if (account != null) {
            return User.builder()
                .username(account.username)
                .password(account.password)
                .authorities(account.roles.map {
                    SimpleGrantedAuthority(it.authority)
                }.toMutableList()).build()
        }
        throw UsernameNotFoundException("Username not found!")
    }
}
