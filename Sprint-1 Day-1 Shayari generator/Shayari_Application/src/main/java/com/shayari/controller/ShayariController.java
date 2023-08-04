package com.shayari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shayari.payload.ChatRequestDTO;
import com.shayari.payload.ChatResponseDTO;

@RestController
@RequestMapping("/generate.in")
@CrossOrigin("*")
public class ShayariController {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String shortNote = " Please generate the response within 2 seconds only.";
	
	
	/**
	 * Used to generate Shayari according to prompt provided and length specified
	 * @param length: short to generate response in 3 to 4 seconds or it will take some time
	 * @param topic: it is the topic on which user want to generate the shayari
	 * @return String
	 */
	@GetMapping("/shayaries")
	public String shayariRequestHandler(
			@RequestParam("length") String length,
			@RequestParam("topic") String topic
			) {
		
		String prompt = "Tell me a shayari about " + topic + " in English.";
		if(length.equalsIgnoreCase("short")) prompt = prompt + shortNote;
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	/**
	 * Used to generate the joke of a specific type
	 * Not using shortNote in this method because all the jokes are always small. so, there is no need
	 * @param type: it is specifies the type of joke (e.g., knock-knock, light bulb, etc.).
	 * @return String
	 */
	@GetMapping("/jokes")
	public String jokeRequestHandler(
			@RequestParam("type") String type
			) {
		
		String prompt = "Tell me a joke on type: " + type + " in English.";
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	
	/**
	 * Used to generate quotes based on chosen theme
	 * Not using shortNote in this method because all the quotes are always small. so, there is no need
	 * @param theme: the quote's theme (love, life, success, etc.).
	 * @return String
	 */
	@GetMapping("/quotes")
	public String quoteRequestHandler(
			@RequestParam("theme") String theme
			) {
		
		String prompt = "Tell me a quote based on the theme: " + " in English.";
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	
	/**
	 * Used to generate stories around some chosen genres
	 * Note: short stories also take some time around 15 to 35 seconds to generate the output
	 * @param length: short to generate response in 15 to 35 seconds or it will take some time
	 * @param genres: on which the story should be based on
	 * @return String
	 */
	@GetMapping("/stories")
	public String storyRequestHandler(
			@RequestParam("length") String length,
			@RequestParam("genres") String[] genres
			) {
		
		String prompt = "Tell me a story with the genres " + String.join(", ", genres) + " in English.";
		if(length.equalsIgnoreCase("short")) prompt = prompt + shortNote;
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	
}
