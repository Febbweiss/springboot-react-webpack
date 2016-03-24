package com.opengroupe.cloud.saas.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opengroupe.cloud.saas.domain.Comment;
import com.opengroupe.cloud.saas.service.CommentService;
import com.opengroupe.cloud.saas.util.React;

@Controller
public class ViewController {
	
	@Autowired
	private CommentService service;
	
	
	@Autowired
    private ObjectMapper objectMapper;

	private React react = new React();
	 
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@RequestMapping("/index")
	public String index(Model model) throws JsonProcessingException {
		List<Comment> comments = service.getAll();
		String commentBox = react.renderCommentBox(comments);
        String data = objectMapper.writeValueAsString(comments);
        model.addAttribute("markup", commentBox);
        model.addAttribute("data", data);
		return "index";
	}
}
