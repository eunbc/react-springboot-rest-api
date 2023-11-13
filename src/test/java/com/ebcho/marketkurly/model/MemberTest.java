package com.ebcho.marketkurly.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;

class MemberTest {

	private Member member;
	private UUID memberId;
	private LocalDateTime createdAt;

	@BeforeEach
	void setUp() {
		memberId = UUID.randomUUID();
		createdAt = LocalDateTime.now();
		member = new Member(memberId, "홍길동", "hong@example.com", "서울특별시 어딘가", createdAt, createdAt);
	}

	@Test
	void 회원_엔티티를_초기화합니다() {
		assertAll("Member initialization",
			() -> assertEquals(memberId, member.getMemberId()),
			() -> assertEquals("홍길동", member.getName()),
			() -> assertEquals("hong@example.com", member.getEmail()),
			() -> assertEquals("서울특별시 어딘가", member.getAddress()),
			() -> assertEquals(createdAt, member.getCreatedAt()),
			() -> assertEquals(createdAt, member.getUpdatedAt())
		);
	}

	@Test
	void 회원의_주소_정보를_변경합니다() {
		String newAddress = "부산광역시 어딘가";
		member.changeAddress(newAddress);

		assertAll("Address change",
			() -> assertEquals(newAddress, member.getAddress()),
			() -> assertTrue(member.getUpdatedAt().isAfter(createdAt))
		);
	}

	@Test
	void 주소_정보가_비어있을_경우_회원정보_변경시_예외가_발생합니다() {
		assertThrows(IllegalArgumentException.class, () -> {
			member.changeAddress(null);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			member.changeAddress("");
		});
	}
}
