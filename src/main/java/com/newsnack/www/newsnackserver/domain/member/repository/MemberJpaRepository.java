package com.newsnack.www.newsnackserver.domain.member.repository;

import com.newsnack.www.newsnackserver.domain.member.model.Member;
import com.newsnack.www.newsnackserver.domain.member.model.MemberStatus;
import com.newsnack.www.newsnackserver.domain.member.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    boolean existsByProviderAndSerialIdAndMemberStatus(Provider provider, String serialId, MemberStatus status);

    Optional<Member> findByProviderAndSerialIdAndMemberStatus(Provider provider, String serialId, MemberStatus status);
}
