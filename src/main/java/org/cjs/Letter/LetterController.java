package org.cjs.Letter;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cjs.article.Article;
import org.cjs.book.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

public class LetterController {
	@Autowired
	LetterDao letterDao;

	Logger logger = LogManager.getLogger();
	
	/**
	 * 받은 목록
	 */
	@GetMapping("/letter/listOfReceiver")
	public void listOfReceiver(@SessionAttribute("MEMBER") Member member,
			Model model) {
		List<Letter> letters = letterDao
				.listLettersOfReceiver(member.getMemberId());
		model.addAttribute("letters", letters);
	}

	/**
	 * 보낸 목록
	 */
	@GetMapping("/letter/listOfSender")
	public void listOfSender(@SessionAttribute("MEMBER") Member member,
			Model model) {
		List<Letter> letters = letterDao
				.listLettersOfSender(member.getMemberId());
		model.addAttribute("letters", letters);
	}

	/**
	 * 보기
	 */
	@GetMapping("/letter/view")
	public void view(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member, Model model) {
		Letter letter = letterDao.getLetter(letterId, member.getMemberId());
		model.addAttribute("letter", letter);
	}
	
	/**
	 * 메일 등록 화면
	 */
	@GetMapping("/letter/addForm")
	public String letterAddForm(HttpSession session) {
		return "letter/addForm";
	}
	/**
	 * 메일 등록
	 */
	@PostMapping("/letter/add")
	public String add(Letter letter, @SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.addLetter(letter);
		return "redirect:/app/members";
	}
	/**
	 * 편지 삭제
	 */
	@GetMapping("/letter/delete")
	public String delete(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member) {
		// TODO: 권한 체크
		letterDao.deleteLetter(letterId, member.getMemberId());
		return "목록 화면";
	}
}
