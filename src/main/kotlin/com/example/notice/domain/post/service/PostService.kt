package com.example.notice.domain.post.service

import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import com.example.notice.domain.post.converter.toEntity
import com.example.notice.domain.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository
) {
    fun save(request: PostSaveRequest, memberId: Long): Long {
        val post = request.toEntity(memberId)

        val savePost = postRepository.save(post)

        return savePost.id
    }

    @Transactional(readOnly = true)
    fun findAll() : List<PostSimpleResponse> {
        return postRepository.findPostSimpleResponses()
    }
}