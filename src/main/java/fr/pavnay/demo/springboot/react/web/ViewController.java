package fr.pavnay.demo.springboot.react.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pavnay.demo.springboot.react.domain.Comment;
import fr.pavnay.demo.springboot.react.service.CommentService;
import fr.pavnay.demo.springboot.react.util.JavaScriptEngine;

@Controller
public class ViewController {

	@Autowired
	private CommentService service;

	@Bean
	JavaScriptEngine nashornEngine() {
		return new JavaScriptEngine().polyfillToNashorn()
				.loadFromClassPath("static/js/vendors.bundle.js")
				.loadFromClassPath("static/js/app.bundle.js");
	}
	
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	JavaScriptEngine nashorn;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/index")
	public String index(Model model) throws JsonProcessingException {
		List<Comment> comments = service.getAll();
		String markup = nashorn.invokeFunction("renderServer", String::valueOf, comments);
		String data = objectMapper.writeValueAsString(comments);
		model.addAttribute("markup", markup);
		model.addAttribute("data", data);
		return "index";
	}
}
