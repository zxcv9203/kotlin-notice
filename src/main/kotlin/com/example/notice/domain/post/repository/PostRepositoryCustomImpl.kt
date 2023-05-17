package com.example.notice.domain.post.repository

import com.example.notice.domain.member.api.dto.response.QMemberResponse
import com.example.notice.domain.post.controller.dto.response.PostResponse
import com.example.notice.domain.post.controller.dto.response.PostSimpleResponse
import com.example.notice.domain.post.controller.dto.response.QPostResponse
import com.example.notice.domain.post.controller.dto.response.QPostSimpleResponse
import com.example.notice.domain.post.model.QPost.post
import com.querydsl.core.types.dsl.BooleanExpression
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
                        post.member.id,
                        post.member.name
                    ),
                    post.createdAt,
                    post.updatedAt
                )
            )
            .from(post)
            .fetch()
    }

    override fun findPostResponse(postId: Long): PostResponse? {
        return queryFactory
            .select(
                QPostResponse(
                    post.id,
                    QMemberResponse(
                        post.member.id,
                        post.member.name
                    ),
                    post.title,
                    post.content,
                    post.createdAt,
                    post.updatedAt
                )
            )
            .from(post)
            .where(eqPostId(postId))
            .fetchOne()
    }

    private fun eqPostId(postId: Long): BooleanExpression = post.id.eq(postId)
}