package com.chj.bootbase.service;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.dto.MemberRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Member findByEmail(String email);
    Member save(MemberRequestDto registration);
    void updatePassword(String password, Long userId);
}
