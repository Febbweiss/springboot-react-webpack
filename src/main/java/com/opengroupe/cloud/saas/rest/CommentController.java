package com.opengroupe.cloud.saas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opengroupe.cloud.saas.domain.Comment;
import com.opengroupe.cloud.saas.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService service;
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value="/api/comments", method=RequestMethod.GET)
	public @ResponseBody List<Comment> comments() {
		return service.getAll();
	}

	@RequestMapping(value="/api/comments", method=RequestMethod.POST)
	public @ResponseBody List<Comment> comments(
			@RequestParam(value="id", required=true) Long id, 
			@RequestParam(value="author", required=true) String author, 
			@RequestParam(value="text", required=true) String text) {
		service.add(new Comment(id, author, text));
		return service.getAll();
	}
	
}
