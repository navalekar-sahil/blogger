package com.example.demo.projection;

import java.time.Instant;
import java.util.List;

import com.example.demo.model.Address;
import com.example.demo.model.Blog;

public interface EmployeeProjection {

	public Integer getId();

	public String getName();

	public String getEmail();

	public Instant getCreateDate();

	public Instant getUpdateDate();

	public Address getAddress();

	public List<Blog> getProject();

}
