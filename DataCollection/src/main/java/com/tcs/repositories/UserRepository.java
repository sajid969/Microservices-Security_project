package com.tcs.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	public List<Object[]> findUsersById(Integer userId) {
		String sql = "SELECT * FROM form WHERE userid = ?";
		return jdbcTemplate.query(sql, new Object[] { userId },
				(rs, rowNum) -> new Object[] { rs.getString("user_name") });
	}

	@SuppressWarnings("deprecation")
	public String getStringDataFromDatabase(Integer planId) {
		String sql = "SELECT plan_name FROM plan WHERE p_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { planId }, String.class);
	}
}
