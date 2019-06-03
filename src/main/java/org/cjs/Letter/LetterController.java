package org.cjs.Letter;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cjs.book.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class LetterController {
	@Autowired
	LetterDao letterDao;

	Logger logger = LogManager.getLogger();
	
	/**
	 * 받은 목록
	 */
	@GetMapping("/letter/listOfReceiver")
	public void listOfReceiver(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {
		
		final int COUNT = 20;
		int offset = (page - 1) * COUNT;
		
		List<Letter> letterlist = letterDao.listLettersOfReceiver(member.getMemberId(), offset, COUNT);
		int totalCount = letterDao.countLettersSent(member.getMemberId());
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("letterList", letterlist);
	}
	/**
	 * 보낸 목록
	*/
	@GetMapping("/letter/listOfSender")
	public void listOfSender(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {
		
		final int COUNT = 20;
		int offset = (page - 1) * COUNT;
		
		List<Letter> letterlist = letterDao.listLettersOfSender(member.getMemberId(), offset, COUNT);
		int totalCount = letterDao.countLettersReceived(member.getMemberId());
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("letterList", letterlist);
	}
	
	/**
	 * 보기
	 */
	@GetMapping("/letter/view")
	public void view(@RequestParam("letterId") String letterId,
			Model model) {
		Letter letter = letterDao.getLetter(letterId);
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
	public String add(Letter letter, 
			@SessionAttribute("MEMBER") Member member){
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.addLetter(letter);
		JOptionPane.showMessageDialog(null, "메일 전송에 성공하였습니다.");
		return "redirect:/app/members";
	}
	/**
	 * 편지 삭제
	 */
	@PostMapping("/letter/delete")
	public String delete(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member,String[] args) {
		letterDao.deleteLetter(letterId, member.getMemberId());
		 String[] buttons = {"받은 메일함", "보낸 메일함"};
	        int num = JOptionPane.showOptionDialog(null, "삭제가 완료되었습니다. 이동할 메일함을 선택해주세요.", "메일 삭제 성공",
	                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "보낸 메일함");
	        if(num == JOptionPane.YES_OPTION) {
	        	return "redirect:/app/letter/listOfReceiver";
	        }else {
		return "redirect:/app/letter/listOfSender";
	    }
	}
}
