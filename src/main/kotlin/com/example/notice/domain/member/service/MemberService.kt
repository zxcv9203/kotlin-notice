package com.example.notice.domain.member.service

import com.example.notice.domain.member.api.dto.request.MemberSaveRequest
import com.example.notice.domain.member.converter.toEntity
import com.example.notice.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun save(request: MemberSaveRequest): Long {
        val member = request.toEntity()
        val saveMember = memberRepository.save(member)

        return saveMember.id
    }
}