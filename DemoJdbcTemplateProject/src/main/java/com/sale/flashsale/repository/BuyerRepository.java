package com.sale.flashsale.repository;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sale.flashsale.model.Buyer;
import com.sale.flashsale.model.FlashSale;

@Repository
public class BuyerRepository {
	@Autowired
	JdbcTemplate template;

	public Buyer getBuyerById(Integer buyerId) {
		Buyer buyer = null;
		try {
			Object o = (Buyer) template.queryForObject("select * from buyer where buyer=? ",
					new Object[] { buyerId }, new BeanPropertyRowMapper<>(Buyer.class));
			
			buyer = (Buyer) o;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return buyer;
	}

	
}
