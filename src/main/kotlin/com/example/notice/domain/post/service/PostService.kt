package com.example.notice.domain.post.service

import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import com.example.notice.domain.post.converter.toEntity
import com.example.notice.domain.post.model.Post
import com.example.notice.domain.post.repository.PostRepository
import com.example.notice.global.exception.InvalidPasswordException
import com.example.notice.global.exception.NotExistPostException
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional(readOnly = true)
    fun findById(postId: Long) : PostResponse {
        val post = postRepository.findPostResponse(postId)

        return post?.let { post }
            ?: throw NotExistPostException(postId)
    }

    fun update(request: PostUpdateRequest, postId: Long): Post {
        val post = this.authPost(request.password, postId)
        post.update(request.title, request.content)

        return post
    }

    fun deleteById(request: PostDeleteRequest, postId: Long) {
        this.authPost(request.password, postId)

        postRepository.deleteById(postId)
    }

    fun authPost(password: String, postId: Long): Post {
        val post = (postRepository.findByIdOrNull(postId)
            ?: throw NotExistPostException(postId))
        if (post.member.password != password) {
            throw InvalidPasswordException(password, post.member.password)
        }

        return post
    }
}