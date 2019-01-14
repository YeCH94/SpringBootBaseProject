package com.chj.bootbase.repository;

import com.chj.bootbase.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Member findPassword(@Param("email") String email);

    @Modifying
    @Query("UPDATE Member m SET m.password = :password WHERE m.email = :email")
    int updatePassword(@Param("email") String email, @Param("password") String password);
}
