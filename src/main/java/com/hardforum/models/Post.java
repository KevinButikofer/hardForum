package com.hardforum.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "post")
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private int id;
	@Column(name="message")
	@NotEmpty(message = "*Please provide a message")
	private String message;
	@Column(name="title")
	@NotEmpty(message = "*Please provide a title")
	private String title;
	@Column(name="score")
	private int score = 0;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
