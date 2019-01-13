package com.chj.bootbase.service;

import com.chj.bootbase.domain.Member;
import com.chj.bootbase.domain.Role;
import com.chj.bootbase.dto.MemberRequestDto;
import com.chj.bootbase.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member save(MemberRequestDto registration) {
        Member member = new Member();

        member.setUsername(registration.getName());
        member.setEmail(registration.getEmail());
        member.setPassword(passwordEncoder.encode(registration.getPassword()));
        member.setRoles(Arrays.asList(new Role("USER")));
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        
        if(member == null ){
            throw new UsernameNotFoundException("E-Mail이나 비밀번호가 유효하지 않습니다.");
        }
        return new org.springframework.security.core.userdetails.User(member.getUsername(),
                member.getPassword(),
                mapRolesToAuthorities(member.getRoles()));
    }

    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getUsername()))
                .collect(Collectors.toList());
    }
}
