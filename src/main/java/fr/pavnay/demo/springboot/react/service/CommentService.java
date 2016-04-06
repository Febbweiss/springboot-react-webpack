package fr.pavnay.demo.springboot.react.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import fr.pavnay.demo.springboot.react.domain.Comment;

@Component
public class CommentService {

	private List<Comment> comments = new ArrayList<Comment>();

	@PostConstruct
	void init() {
		comments.addAll(Arrays.asList(new Comment(1L, "Pete Hunt", "This is one comment"),
				new Comment(2L, "Jordan Walke", "This is *another* comment")));
	}
	
	public void add(Comment comment) {
		comments.add(comment);
	}
	
	public List<Comment> getAll() {
		return comments;
	}
}
