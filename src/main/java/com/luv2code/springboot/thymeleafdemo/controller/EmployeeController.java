package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee employee = new Employee();

		theModel.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee){

		//save the employee
		employeeService.save(employee);

		// use a redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		Employee employee = employeeService.findById(theId);

		theModel.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}

}









