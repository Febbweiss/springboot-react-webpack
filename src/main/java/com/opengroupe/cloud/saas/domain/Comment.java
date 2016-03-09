package com.opengroupe.cloud.saas.domain;

import groovy.transform.ToString;

@ToString
public class Comment {

	private final Long id;
	private final String author;
	private final String text;
	
	public Comment(Long id, String author, String text) {
		this.id = id;
		this.author = author;
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public String getAuthor() {
		return author;
	}
	public String getText() {
		return text;
	}
}
