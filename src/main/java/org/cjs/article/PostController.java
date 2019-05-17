package org.cjs.article;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
	@Autowired
	ArticleDao articleDao;

	@GetMapping("/article/list")
	public void articleList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Article> articleList = articleDao.listArticles(offset, COUNT);
		int totalCount = articleDao.getArticlesCount();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articleList", articleList);
	}

	@GetMapping("/article/view")
	public void articleView(@RequestParam("articleId") String articleId,
			Model model) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article", article);
	}

	@GetMapping("/article/addForm")
	public String articleAddForm(HttpSession session) {
		return "article/addForm";
	}

	@PostMapping("/article/add")
	public String articleAdd(Article article, HttpSession session) {
		article.setUserId("2016041111");
		article.setName("최준수");
		articleDao.addArticle(article);
		return "redirect:/app/article/list";
	}
}