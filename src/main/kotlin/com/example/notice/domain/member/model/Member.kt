package com.example.notice.domain.member.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0L,
    val name: String,
    val password: String,
)