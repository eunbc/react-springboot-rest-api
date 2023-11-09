package com.ebcho.marketkurly.view;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ebcho.marketkurly.controller.dto.member.CreateMemberRequest;
import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.service.MemberService;

@Controller
public class AdminMemberController {

	private final MemberService memberService;

	public AdminMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	public String getMembersAll(Model model) {
		List<MemberResponse> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "member/list";
	}

	@GetMapping("/members/{memberId}")
	public String getMemberDetail(@PathVariable UUID memberId, Model model) {
		MemberResponse member = memberService.getMemberById(memberId);
		model.addAttribute("member", member);
		return "member/detail";
	}

	@GetMapping("/members/create")
	public String getMemberCreateForm() {
		return "member/create";
	}

	@GetMapping("/members/update/{memberId}")
	public String getMemberUpdateForm(@PathVariable UUID memberId, Model model) {
		MemberResponse member = memberService.getMemberById(memberId);
		model.addAttribute("member", member);
		return "member/update";
	}

}
