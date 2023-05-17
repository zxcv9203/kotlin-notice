package com.example.notice.domain.post.model

import com.example.notice.domain.member.model.Member
import com.example.notice.global.model.BaseEntity
import jakarta.persistence.*

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member
) : BaseEntity() {

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}