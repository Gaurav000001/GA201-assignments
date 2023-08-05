package com.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.converter.payload.ChatRequestDTO;
import com.converter.payload.ChatResponseDTO;

@RestController
@RequestMapping("/convert.in")
@CrossOrigin("*")
public class CodeConverterController {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String shortNote = " Please generate the response within 2 seconds only.";
	
	
	/**
	 * Used to convert the code to target language set by the user
	 * @param language
	 * @param code
	 * @return
	 */
	@PostMapping("/convert")
	public String codeConverterRequestHandler(
			@RequestParam("targetLanguage") String language,
			@RequestBody String code
			) {
		
		String prompt = "Act as a Language Converter and convert the whole code provided below \n\n If you find any issue with the code provided by me please throw relevant errors.\n\n Convert the code in: "+ language +"\n\n Code: \n\n" + code;;
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	
	/**
	 * Used to debug the code provided by user in language selected by user if possible 
	 * else it will provide the relevant message to the user
	 * @param language
	 * @param code
	 * @return
	 */
	@PostMapping("/debug")
	public String DebugCodeRequestHandler(
			@RequestParam("targetLanguage") String language,
			@RequestBody String code
			) {
		
		String prompt = "Provided a piece of code in "+ language +"\n Act as a debugger and debug the below code by providing step by step explaination: " + code;
		
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
	@PostMapping("/check")
	public ChatResponseDTO quoteRequestHandler(
			@RequestBody String code
			) {
		
		String prompt = "Act as a code Checker and suggest necessary modifications if applicable else provide the message `Everything is correct You can go ahead to run your code`, code: " + code;
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
		
//		return response.getChoices().get(0).getMessage().getContent();
		
		return response;
	}
	
	
	@PostMapping("/run")
	public String runRequestHandler(
			@RequestBody String code
			) {
		
		String prompt = "Act as a console and output this code below, please don't provide any kind of explaination, if code is having any syntactical error just provide the error message: \n\n" + code;
		
		ChatRequestDTO request = new ChatRequestDTO(model, prompt);
		
		ChatResponseDTO response = restTemplate.postForObject(apiUrl, request, ChatResponseDTO.class);
				
		return response.getChoices().get(0).getMessage().getContent();
		
//		return response;
	}
	
	
	
}
