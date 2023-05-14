package com.example.notice.domain.post.repository

import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse

interface PostRepositoryCustom {
    fun findPostSimpleResponses(): List<PostSimpleResponse>

}