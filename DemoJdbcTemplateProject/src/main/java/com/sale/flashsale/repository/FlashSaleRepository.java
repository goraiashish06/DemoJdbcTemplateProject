package com.sale.flashsale.repository;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.model.Registration;

@Repository
public class FlashSaleRepository {

	@Autowired
	JdbcTemplate template;

	public FlashSale getFlashSaleById(Integer flashsaleId) {
		FlashSale flashsale = null;
		try {
			Object o = (FlashSale) template.queryForObject("select * from Flashsale where flashsaleid=? ",
					new Object[] { flashsaleId }, new BeanPropertyRowMapper<>(FlashSale.class));

			flashsale = (FlashSale) o;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return flashsale;
	}

	public Integer createFlashsale(Product pr, FlashSale f) {
		String query = "INSERT INTO flashsale VALUES(?,?,?,?,?)";
		Random rand = new Random();
		int n = rand.nextInt(50);
		int i=template.update(query, n, "Created", pr.getId(), f.getFlashSlaeDate(),f.getRegistrationOpen());
		if(i>0) {
			return n;
		}
		return 0;
	}
	
	public Integer startFlashsale(FlashSale f) {
		String query = "UPDATE flashsale SET status = ?,registrationopen=? WHERE id=?  ";
		Random rand = new Random();
		int n = rand.nextInt(50);
		return template.update(query, "Started", "Closed", f.getId());
	}
	
	public Integer stopFlashsale(FlashSale f) {
		String query = "UPDATE flashsale SET status = ?,registrationopen=? WHERE id=?  ";
		Random rand = new Random();
		int n = rand.nextInt(50);
		return template.update(query, "Stopped", "Closed", f.getId());
	}

}
