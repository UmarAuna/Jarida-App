package com.example.jaridaapp.jaridaV1.model;

import com.google.gson.annotations.SerializedName;

public class JaridaListItem{

	@SerializedName("author")
	private String author;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("content")
	private String content;

	public String getAuthor(){
		return author;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getContent(){
		return content;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
 	public String toString(){
		return 
			"JaridaListItem{" + 
			"author = '" + author + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}