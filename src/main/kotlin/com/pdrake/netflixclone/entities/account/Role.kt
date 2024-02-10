package com.pdrake.netflixclone.entities.account

import com.pdrake.netflixclone.entities.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "role")
class Role(
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,
    @Column(name = "authority", length = 30, nullable = false)
    val authority: String
) : BaseEntity()
