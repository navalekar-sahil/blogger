package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Blog;

public interface ProjectRepo extends CrudRepository<Blog, Integer> {

}
