package com.chj.bootbase.repository;

import com.chj.bootbase.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    @Query("select a from Member a where a.email = :email")
    Member findPassword(@Param("email") String email);
}
