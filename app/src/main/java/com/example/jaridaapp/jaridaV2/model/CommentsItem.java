package com.example.jaridaapp.jaridaV2.model;

import com.google.gson.annotations.SerializedName;

public class CommentsItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("text")
	private String text;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}