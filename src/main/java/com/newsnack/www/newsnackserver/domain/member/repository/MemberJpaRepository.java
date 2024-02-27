package com.newsnack.www.newsnackserver.domain.member.repository;

import com.newsnack.www.newsnackserver.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
