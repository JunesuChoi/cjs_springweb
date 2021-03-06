package org.cjs.article;

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
public class ArticleController {

	@Autowired
	ArticleDao articleDao;

	Logger logger = LogManager.getLogger();

	/**
	 * 글 목록
	 */
	@GetMapping("/article/list")
	public void articleList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Article> articleList = articleDao.listArticles(offset, COUNT);
		int totalCount = articleDao.getArticlesCount();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articleList", articleList);
	}

	/**
	 * 글 보기
	 */
	@GetMapping("/article/view")
	public void articleView(@RequestParam("articleId") String articleId,
			Model model) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article", article);
	}

	/**
	 * 글 등록 화면
	 */
	@GetMapping("/article/s/addForm")
	public String articleAddForm(HttpSession session) {
		return "article/s/addForm";
	}

	/**
	 * 글 등록
	 */
	@PostMapping("/article/s/add")
	public String articleAdd(Article article,
			@SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.addArticle(article);
		return "redirect:/app/article/list";
	}
	
	/**
	 *  글 수정 폼
	 */
	@GetMapping("/article/s/modifyForm")
	public void articleupdateForm(Article article,HttpSession session,
			@RequestParam("articleId") String articleId,
			@SessionAttribute("MEMBER") Member member, Model model) {
		article = articleDao.getArticle(articleId);
		article.getUserId();
		model.addAttribute("article", article);
	}
	/**
	 *  글 수정 
	 */
	@PostMapping("/article/s/modify")
	public String articleUpdate(Article article,HttpSession session,
			@SessionAttribute("MEMBER") Member member) {
		article.setArticleId(article.articleId);
		articleDao.modifyArticle(article);
		JOptionPane.showMessageDialog(null, "수정 완료");
		return "redirect:/app/article/list";
}
	
	/**
	 * 글 삭제
	 */
	@PostMapping("/article/s/delete")
	public String articledelete(Article article,HttpSession session,
			@RequestParam("articleId") String articleId,
			@SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		article = articleDao.getArticle(articleId);
		if(!member.getMemberId().equals(article.getUserId())){
			JOptionPane.showMessageDialog(null, "본인이 작성한 게시글만 삭제가능합니다.", "삭제 실패", JOptionPane.ERROR_MESSAGE);
			return "redirect:/app/article/view?articleId="+articleId;
		} else {
			articleDao.deleteArticle(article);
		JOptionPane.showMessageDialog(null, "삭제 완료");
		return "redirect:/app/article/list";
		}
	}
}