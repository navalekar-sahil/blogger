package com.example.demo.services;

import java.nio.file.FileSystemLoopException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Address;
import com.example.demo.model.EmployeeModel;
import com.example.demo.model.Blog;
import com.example.demo.projection.EmployeeProjection;
import com.example.demo.repo.AddressRepo;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.ProjectRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	ProjectRepo projectRepo;

	public EmployeeModel getEmployeeWithDetails(int id) {
		return this.employeeRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id " + id);
		});
	}

	public EmployeeModel createUser(EmployeeModel employeeModel) {
		return this.employeeRepo.save(employeeModel);
	}

	public Iterable<EmployeeProjection> getAllUser() {
		return this.employeeRepo.findEmployeeBy();
	}

	public EmployeeProjection getUserById(int id) {
		return this.employeeRepo.findEmployeeById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id " + id);
		});
	}

	// searching with name containing
	public Iterable<EmployeeProjection> getEmployeeByNameContaining(String name) {
		return this.employeeRepo.findByNameContaining(name);
	}

	// searching with employee name
	public Iterable<EmployeeProjection> getEmployeeByName(String name) {
		return this.employeeRepo.findByName(name);
	}

	// searching name and email containing
	public Iterable<EmployeeProjection> getByNameOrEmailContaining(String name, String email) {
		return this.employeeRepo.findByNameContainingOrEmailContaining(name, email);
	}

	// searching with both name and email
	public Iterable<EmployeeProjection> getEmployeeWithNameAndEmail(String name, String email) {
		return this.employeeRepo.findByNameContainingAndEmailContaining(name, email);
	}

	public void delete(int id) {
		this.employeeRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found with id " + id);
		});
		this.employeeRepo.deleteById(id);
	}

	public EmployeeModel update(int id, EmployeeModel employeeModel) {
		EmployeeModel empData = getEmployeeWithDetails(id);
		employeeModel.setId(id);
		employeeModel.setCreateDate(empData.getCreateDate());
		return this.employeeRepo.save(employeeModel);
	}

	// add address
	public Address createAddress(int id, Address address) {
		EmployeeModel empData = getEmployeeWithDetails(id);
		Address addressData = this.addressRepo.save(address);
		empData.setAddress(addressData);
		this.employeeRepo.save(empData);
		return addressData;
	}

	// update address
	public Address updateAddress(int id, Address address) {
		EmployeeModel empData = getEmployeeWithDetails(id);
		Address addressData = this.addressRepo.save(address);
		empData.setAddress(addressData);
		this.employeeRepo.save(empData);
		return addressData;
	}

	// inserting empoyee project
	public Blog addProject(int id, Blog Blog) {
		EmployeeModel employeeData = getEmployeeWithDetails(id);
		Blog.setEmployee(employeeData);
		Blog projectData = this.projectRepo.save(Blog);
		return projectData;

	}
	
	//display all project of perticular empoyee
	
	public List<Blog> getAllProject(int id){
		EmployeeModel employeeData=getEmployeeWithDetails(id);
		return employeeData.getProject();
	}

}
