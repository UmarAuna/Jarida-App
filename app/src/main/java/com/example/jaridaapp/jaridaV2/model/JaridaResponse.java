package com.example.jaridaapp.jaridaV2.model;

import java.util.List;

public class JaridaResponse{
	private int number;
	private boolean last;
	private int size;
	private int numberOfElements;
	private int totalPages;
	private Pageable pageable;
	private Sort sort;
	private List<ContentItem> content;
	private boolean first;
	private int totalElements;
	private boolean empty;

	public int getNumber(){
		return number;
	}

	public boolean isLast(){
		return last;
	}

	public int getSize(){
		return size;
	}

	public int getNumberOfElements(){
		return numberOfElements;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public Pageable getPageable(){
		return pageable;
	}

	public Sort getSort(){
		return sort;
	}

	public List<ContentItem> getContent(){
		return content;
	}

	public boolean isFirst(){
		return first;
	}

	public int getTotalElements(){
		return totalElements;
	}

	public boolean isEmpty(){
		return empty;
	}
}