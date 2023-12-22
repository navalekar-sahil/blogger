package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.EmployeeModel;
import com.example.demo.projection.EmployeeProjection;

public interface EmployeeRepo extends CrudRepository<EmployeeModel, Integer> {

	public Iterable<EmployeeProjection> findEmployeeBy();
	
	public Optional<EmployeeProjection> findEmployeeById(int id);
	
	public Iterable<EmployeeProjection> findByNameContaining(String name);
	
	public Iterable<EmployeeProjection> findByName(String name);
	
	public Iterable<EmployeeProjection> findByNameContainingOrEmailContaining(String name,String email);
	
	public Iterable<EmployeeProjection> findByNameContainingAndEmailContaining(String name,String email);
}
