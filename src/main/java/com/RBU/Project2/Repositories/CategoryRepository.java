package com.RBU.Project2.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RBU.Project2.entities.Category;
import com.RBU.Project2.entities.Task;
import com.RBU.Project2.entities.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public User findByUserId(Long userId);
	
	public List<Task> findTaskByUserId(Long userId);
	
	public List<Category> findCategoryByUserId(Long userId);
	
	Category findByName(String name);

}
