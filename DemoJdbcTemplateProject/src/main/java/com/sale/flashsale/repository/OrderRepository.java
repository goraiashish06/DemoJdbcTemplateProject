package com.sale.flashsale.repository;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sale.flashsale.model.Buyer;
import com.sale.flashsale.model.Order;

public class OrderRepository {
	@Autowired
	JdbcTemplate template;
	
	public Integer saveOrder(Order oo) {
		
		 String query = "INSERT INTO Orders VALUES(?,?,?,?)";
		 Random rand = new Random();
		 int n = rand.nextInt(50);
	        return template.update(query,n,oo.getProduct(),oo.getBuyerId(),oo.getCreatedAt(),oo.getUpdatedAt());
	}

}
