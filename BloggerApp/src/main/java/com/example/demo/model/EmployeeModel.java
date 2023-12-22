package com.example.demo.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.Collate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@NotBlank
	@Size(min = 3,max=30)
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@NotBlank
	@Email
	@Column(nullable = false)
	private String email;

	@NotBlank
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]+",message = "password should be combination of characte and numbers")
	@Column(nullable = false)
	private String password;
	
	@CreatedDate
	private Instant createDate;
	
	@LastModifiedDate
	private Instant updateDate;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "employee")
	private List<Blog> blog;
	

	public List<Blog> getProject() {
		return blog;
	}

	public void setProject(List<Blog> Blog) {
		this.blog = Blog;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeeModel() {

	}

	public EmployeeModel(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public Instant getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Instant updateDate) {
		this.updateDate = updateDate;
	}

	
}
