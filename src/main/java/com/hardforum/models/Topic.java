package com.hardforum.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "topic")
public class Topic {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private int id;
	@Column(name="message")
	@NotEmpty(message = "*Please provide a message")
	private String message;
	@Column(name="name")
	private String name;
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
