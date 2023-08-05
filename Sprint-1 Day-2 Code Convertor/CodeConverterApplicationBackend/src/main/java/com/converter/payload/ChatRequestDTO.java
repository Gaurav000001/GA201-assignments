package com.converter.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatRequestDTO {
	
	private String model;
	private List<Message> messages = new ArrayList<>();
	
	public ChatRequestDTO(String model, String prompt) {
		this.model = model;
		this.messages.add(new Message("user", prompt));
	}
}
