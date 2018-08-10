package com.springboot.web.springbootfirstwebapplication.controller;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.web.springbootfirstwebapplication.model.Todo;
import com.springboot.web.springbootfirstwebapplication.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	@Autowired
	TodoService service;
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String listthetodos(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showaddtodopage(ModelMap model) {
		//service.addTodo((String) model.get("name"), description, new Date(0), false);
		model.addAttribute("todo",new Todo(0, (String) model.get("name"), "Default Desc", new Date(0), false));
		return "todo";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String updateTodo(Todo todo){
		
		return "todo";
	}
	
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addthosetodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		service.addTodo((String) model.get("name"), todo.getDesc(), new Date(0), false);
		
		return "redirect:/list-todos";
	}
		
}
