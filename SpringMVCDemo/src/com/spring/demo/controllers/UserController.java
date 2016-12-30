package com.spring.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.demo.domain.JsonResponse;
import com.spring.demo.domain.User;

@Controller
public class UserController {
	
	private List<User> userList = new ArrayList<User>(); 
	
	@RequestMapping(value="/AddUser.html",method=RequestMethod.GET)
	public String showForm(){
		//Any thing i can do based on the business req. 
		//From here i can go to service layer and database layer
		//But for now am return to View layer
		System.out.println("Am from the showForm :::::: ");
		return "AddUser";
	}
	
	@RequestMapping(value="/AddUser.html",method=RequestMethod.POST)
	public @ResponseBody JsonResponse addUser(@ModelAttribute(value="user") User user, BindingResult result ){
		JsonResponse res = new JsonResponse();
		ValidationUtils.rejectIfEmpty(result, "name", "Name can not be empty.");
		ValidationUtils.rejectIfEmpty(result, "education", "Educatioan not be empty");
		if(!result.hasErrors()){
			userList.add(user);
			res.setStatus("SUCCESS");
			res.setResult(userList);
		}else{
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}
		//Real world .. it is the place where it will go for service layer / business validations / database
		
		return res;
	}

}
