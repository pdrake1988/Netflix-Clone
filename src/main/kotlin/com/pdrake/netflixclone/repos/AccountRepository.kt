package com.pdrake.netflixclone.repos

import com.pdrake.netflixclone.entities.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Int> {
    fun findAccountByUsername(username: String?): Account?
}
