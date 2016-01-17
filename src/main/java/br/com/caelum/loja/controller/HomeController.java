package br.com.caelum.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(){
		System.out.println("Olá Spring!");
		return "home";
	}

}
