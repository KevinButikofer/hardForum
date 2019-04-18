package com.hardforum.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Table(name = "subforum")
public class SubForum {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="subForum_id")
	private int id;
	@Column(name="name")
	@NotEmpty(message = "*Please provide a subforum name")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private User subForum_admin;
}
