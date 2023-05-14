package com.example.notice.domain.post.repository

import com.example.notice.domain.member.api.dto.response.MemberResponse
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDateTime

class PostRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : PostRepositoryCustom {

    override fun findPostSimpleResponses(): List<PostSimpleResponse> {
        return listOf(
            PostSimpleResponse(
                id = 1,
                title = "테스트 이름1",
                member = MemberResponse(
                    id = 1,
                    nickName = "테스트 유저1"
                ),
                LocalDateTime.now(),
                LocalDateTime.now()
            ),
            PostSimpleResponse(
                id = 2,
                title = "테스트 이름2",
                member = MemberResponse(
                    id = 2,
                    nickName = "테스트 유저2"
                ),
                LocalDateTime.now(),
                LocalDateTime.now()
            ),
            PostSimpleResponse(
                id = 3,
                title = "테스트 이름3",
                member = MemberResponse(
                    id = 3,
                    nickName = "테스트 유저3"
                ),
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        )
    }
}