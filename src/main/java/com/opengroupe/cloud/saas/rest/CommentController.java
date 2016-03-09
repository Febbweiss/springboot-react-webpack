package com.opengroupe.cloud.saas.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opengroupe.cloud.saas.domain.Comment;

@RestController
public class CommentController {

	private List<Comment> comments = new ArrayList<Comment>();

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value="/api/comments", method=RequestMethod.GET)
	public @ResponseBody List<Comment> comments() {
		return comments;
	}

	@RequestMapping(value="/api/comments", method=RequestMethod.POST)
	public @ResponseBody List<Comment> comments(
			@RequestParam(value="id", required=true) Long id, 
			@RequestParam(value="author", required=true) String author, 
			@RequestParam(value="text", required=true) String text) {
		comments.add(new Comment(id, author, text));
		return comments;
	}
}
