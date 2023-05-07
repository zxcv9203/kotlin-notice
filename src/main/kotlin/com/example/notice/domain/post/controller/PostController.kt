package com.example.notice.domain.post.controller

import com.example.notice.domain.post.controller.dto.request.PostDeleteRequest
import com.example.notice.domain.post.controller.dto.request.PostSaveRequest
import com.example.notice.domain.post.controller.dto.request.PostUpdateRequest
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostResponses
import com.example.notice.domain.post.facade.PostFacade
import com.example.notice.global.model.ApiResponse
import com.example.notice.global.model.IdResponse
import com.example.notice.global.util.toUri
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postFacade: PostFacade
) {

    @GetMapping
    fun findAll(
    ): ResponseEntity<ApiResponse<PostResponses>> {
        val response = postFacade.findAll()

        return ResponseEntity.ok()
            .body(ApiResponse.success(OK.value(), "게시글 조회 성공", response))
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<PostResponse>> {
        val response = postFacade.findById(id)

        return ResponseEntity.ok()
            .body(ApiResponse.success(OK.value(), "게시글 단건 조회 성공", response))
    }

    @PostMapping
    fun save(
        @RequestBody request: PostSaveRequest
    ): ResponseEntity<ApiResponse<IdResponse>> {
        val response = postFacade.save(request)

        return ResponseEntity.created(response.toUri())
            .body(ApiResponse.success(CREATED.value(), "게시글 생성 성공", response))
    }

    @PatchMapping("/{id}")
    fun update(
        @RequestBody request: PostUpdateRequest,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<PostResponse>> {
        val response = postFacade.update(request, id)

        return ResponseEntity.ok()
            .body(ApiResponse.success(OK.value(), "게시글 수정이 완료되었습니다.", response))
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @RequestBody request: PostDeleteRequest,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<Unit>> {
        postFacade.deleteById(request, id)

        return ResponseEntity.ok()
            .body(ApiResponse.success(OK.value(), "게시글이 삭제 되었습니다."))
    }
}
