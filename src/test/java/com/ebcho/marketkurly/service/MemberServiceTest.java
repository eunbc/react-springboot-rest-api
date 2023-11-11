package com.ebcho.marketkurly.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ebcho.marketkurly.controller.dto.member.CreateMemberRequest;
import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.controller.dto.member.UpdateMemberRequest;
import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.repository.MemberRepository;

class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	private UUID memberId;
	private Member member;
	private CreateMemberRequest createRequest;
	private UpdateMemberRequest updateRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		memberId = UUID.randomUUID();
		member = new Member(memberId, "홍길동", "hong@example.com", "서울특별시 어딘가", LocalDateTime.now(),
			LocalDateTime.now());
		createRequest = new CreateMemberRequest("홍길동", "hong@example.com", "서울특별시 어딘가");
		updateRequest = new UpdateMemberRequest("서울특별시 어딘가");
	}

	@Test
	void 회원을_생성합니다() {
		when(memberRepository.insert(any())).thenReturn(member);

		MemberResponse response = memberService.createMember(createRequest);

		verify(memberRepository).insert(any());
		assertThat(response.name(), is("홍길동"));
	}

	@Test
	void 전체_회원_목록을_조회합니다() {
		when(memberRepository.findAll()).thenReturn(List.of(member));

		List<MemberResponse> members = memberService.getAllMembers();

		verify(memberRepository).findAll();
		assertThat(members, hasSize(1));
		assertThat(members.get(0).name(), is("홍길동"));
	}

	@Test
	void 회원_아이디로_회원을_조회합니다() {
		when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

		MemberResponse foundMember = memberService.getMemberById(memberId);

		verify(memberRepository).findById(memberId);
		assertThat(foundMember.name(), is("홍길동"));
	}

	@Test
	void 회원_정보를_변경합니다() {
		when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
		when(memberRepository.update(any())).thenReturn(member);

		MemberResponse updatedMember = memberService.updateMember(memberId, updateRequest);

		verify(memberRepository).findById(memberId);
		verify(memberRepository).update(any());
		assertThat(updatedMember.address(), is("서울특별시 어딘가"));
	}

	@Test
	void 회원을_삭제합니다() {
		when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

		memberService.deleteMember(memberId);

		verify(memberRepository).findById(memberId);
		verify(memberRepository).deleteById(memberId);
	}

}
