package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
