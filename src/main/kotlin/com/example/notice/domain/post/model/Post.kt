package com.example.notice.domain.post.model

import com.example.notice.global.model.BaseEntity
import jakarta.persistence.*

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,

    @Column(name = "member_id")
    val memberId: Long,

    @Column(name = "title")
    val title: String,

    @Column(name = "content")
    val content: String,
) : BaseEntity()