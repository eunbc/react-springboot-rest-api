package com.ebcho.marketkurly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ebcho.marketkurly.model.Member;

public interface MemberRepository {

	Member insert(Member member);

	Member update(Member member);

	List<Member> findAll();

	Optional<Member> findById(UUID memberId);

	void deleteById(UUID memberId);
}
