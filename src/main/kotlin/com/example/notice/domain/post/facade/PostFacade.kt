package com.example.notice.domain.post.facade

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.example.notice.domain.member.converter.toMemberSaveRequest
import com.example.notice.domain.member.service.MemberService
import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostResponses
import com.example.notice.domain.post.service.PostService
import com.example.notice.global.model.IdResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PostFacade(
    private val postService: PostService,
    private val memberService: MemberService
) {

    fun findAll(): PostResponses {
        val posts = postService.findAll()

        return PostResponses(posts)
    }

    fun findById(postId: Long): PostResponse {
        return postService.findById(postId)
    }

    fun save(request: PostSaveRequest): IdResponse {
        val memberId = memberService.save(request.toMemberSaveRequest())

        val postId = postService.save(request, memberId)

        return IdResponse(postId)
    }

    fun update(request: PostUpdateRequest, postId: Long): PostResponse {
        // 게시글 및 패스워드 확인
        val post = postService.update(request, postId)

        return PostResponse(
            id = post.id,
            member = MemberResponse(
                id = post.id,
                nickName = post.member.name,
            ),
            title = post.title,
            content = post.content,
            createdAt = post.createdAt,
            updatedAt = post.updatedAt,
        )
    }

    fun deleteById(request: PostDeleteRequest, postId: Long) {
        postService.deleteById(request, postId)
    }
}