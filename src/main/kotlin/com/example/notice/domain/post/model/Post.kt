package com.example.notice.domain.post.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
) {
}