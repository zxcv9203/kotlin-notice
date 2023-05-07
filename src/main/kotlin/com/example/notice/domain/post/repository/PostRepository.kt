package com.example.notice.domain.post.repository

import com.example.notice.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<Post, Long>{
}