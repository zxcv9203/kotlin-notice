package com.example.notice.domain.post.repository

import com.example.notice.domain.member.api.dto.response.QMemberResponse
import com.example.notice.domain.member.model.QMember.member
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import com.example.notice.domain.post.controller.dto.response.QPostSimpleResponse
import com.example.notice.domain.post.model.QPost.post
import com.querydsl.jpa.impl.JPAQueryFactory

class PostRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : PostRepositoryCustom {

    override fun findPostSimpleResponses(): List<PostSimpleResponse> {
        return queryFactory
            .select(
                QPostSimpleResponse(
                    post.id,
                    post.title,
                    QMemberResponse(
                        member.id,
                        member.name
                    ),
                    post.createdAt,
                    post.updatedAt
                )
            )
            .from(post)
            .join(member)
            .on(post.memberId.eq(member.id))
            .fetch()
    }
}