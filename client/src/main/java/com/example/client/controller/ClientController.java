package com.example.client.controller;

import com.example.client.entity.Input;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {

    @GetMapping("/index")
    public String inputForm(Model model) {
        model.addAttribute("input", new Input());
        return "input_form";
    }

    @PostMapping("/index")
    public String inputFormSubmit(@ModelAttribute Input input, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(input.getContent(), headers);
        String result = restTemplate.postForObject("http://172.1.1.11:8081/publish", entity, String.class);
        model.addAttribute("result", result);
        return "result_form";
    }
}
