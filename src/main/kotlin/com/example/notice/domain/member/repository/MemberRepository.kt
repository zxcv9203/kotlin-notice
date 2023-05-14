package com.example.notice.domain.member.repository

import com.example.notice.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
}