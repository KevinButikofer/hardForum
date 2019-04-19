package com.hardforum.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "topic")
public class Topic {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="topic_id")
	private int id;
	
	@Column(name="message", columnDefinition="LONGTEXT")
	@NotEmpty(message = "*Please provide a message")
	private String message;
	@Column(name="name")
	private String name;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="topic")
	@Nullable
	private List<Post> posts;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User author;
	 
	public User getAuthor() {
		return author;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public SubForum getSubForum() {
		return subForum;
	}
	public void setSubForum(SubForum subForum) {
		this.subForum = subForum;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="subForum_id")
	 private SubForum subForum;
	
	public String getName() {
		return name;
	}
	


	public void setName(String name) {
		this.name = name;
	}
	public int getNbPostedMessage() {
		return nbPostedMessage;
	}
	public void setNbPostedMessage(int nbPostedMessage) {
		this.nbPostedMessage = nbPostedMessage;
	}
	@Column(name="nbPost")
	private int nbPostedMessage = 0;
	
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
