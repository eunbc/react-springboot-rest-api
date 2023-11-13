package com.ebcho.marketkurly.repository;

import static com.ebcho.marketkurly.util.JdbcUtils.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ebcho.marketkurly.model.Member;

@Repository
public class MemberJdbcRepository implements MemberRepository {

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
		UUID memberId = toUUID(rs.getBytes("member_id"));
		String name = rs.getString("name");
		String email = rs.getString("email");
		String address = rs.getString("address");
		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		LocalDateTime updatedAt =
			rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null;
		return new Member(memberId, name, email, address, createdAt, updatedAt);
	};

	public MemberJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Member insert(Member member) {
		final String sql = "INSERT INTO member (member_id, name, email, address, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, toBytes(member.getMemberId()), member.getName(), member.getEmail(),
			member.getAddress(),
			member.getCreatedAt(), member.getUpdatedAt());
		return member;
	}

	@Override
	public Member update(Member member) {
		final String sql = "UPDATE member SET name = ?, email = ?, address = ?, updated_at = ? WHERE member_id = ?";
		jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getAddress(), member.getUpdatedAt(),
			toBytes(member.getMemberId()));
		return member;
	}

	@Override
	public List<Member> findAll() {
		final String sql = "SELECT * FROM member";
		return jdbcTemplate.query(sql, memberRowMapper);
	}

	@Override
	public Optional<Member> findById(UUID memberId) {
		final String sql = "SELECT * FROM member WHERE member_id = ?";
		List<Member> members = jdbcTemplate.query(sql, memberRowMapper, toBytes(memberId));
		return members.stream().findFirst();
	}

	@Override
	public void deleteById(UUID memberId) {
		final String sql = "DELETE FROM member WHERE member_id = ?";
		jdbcTemplate.update(sql, toBytes(memberId));
	}

}
