package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Address;
import com.example.demo.model.EmployeeModel;
import com.example.demo.model.Blog;
import com.example.demo.services.EmployeeService;
import com.example.demo.wrapper.ResponseWrapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("")
	public ResponseEntity createUser(@RequestBody @Valid EmployeeModel employeeModel) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("user created ");
		responseWrapper.setData(this.service.createUser(employeeModel));
		return new ResponseEntity(responseWrapper, HttpStatus.CREATED);

	}

	@GetMapping("")
	public ResponseEntity getAlluser() {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("All Users");
		responseWrapper.setData(this.service.getAllUser());
		return new ResponseEntity(responseWrapper, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity getUserById(@PathVariable int id) {

		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("User data of id=" + id);
		responseWrapper.setData(this.service.getUserById(id));
		return new ResponseEntity(responseWrapper, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable int id) {

		this.service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody EmployeeModel employeeModel) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("update data sucessefully");
		responseWrapper.setData(this.service.update(id, employeeModel));
		return new ResponseEntity(responseWrapper, HttpStatus.OK);
	}



	// inserting address

	@PostMapping("/{id}/address")
	public ResponseEntity insertAddress(@PathVariable int id, @RequestBody Address address) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("adderss inserted sucessefully");
		responseWrapper.setData(this.service.createAddress(id, address));
		return new ResponseEntity(responseWrapper, HttpStatus.OK);
	}

	// update Address
	@PutMapping("/{id}/updateaddress")
	public ResponseEntity updateAddress(@PathVariable int id, @RequestBody Address address) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setMessage("adderss updated sucessefully");
		responseWrapper.setData(this.service.updateAddress(id, address));
		return new ResponseEntity(responseWrapper, HttpStatus.OK);
	}
	
	
	
	// =======================================================================================//

	@PostMapping("/{id}/blog")
	public ResponseEntity insertProject(@PathVariable int id,@RequestBody Blog Blog) {
		ResponseWrapper responseWrapper=new ResponseWrapper();
		responseWrapper.setMessage("blog iserted successfully");
		responseWrapper.setData(this.service.addProject(id, Blog));
		return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/blog")
	public ResponseEntity getAllProject(@PathVariable int id) {
		ResponseWrapper responseWrapper=new ResponseWrapper();
		responseWrapper.setMessage("all blogs of user id: "+id);
		responseWrapper.setData(this.service.getAllProject(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
}
