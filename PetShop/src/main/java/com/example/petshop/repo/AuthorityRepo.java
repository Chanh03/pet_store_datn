package com.example.petshop.repo;

import com.example.petshop.entity.Authority;
import com.example.petshop.entity.User;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
	@Query("DELETE FROM Authorities a WHERE a.userName := userName")
	void deleteByUserName(User userName);
}
