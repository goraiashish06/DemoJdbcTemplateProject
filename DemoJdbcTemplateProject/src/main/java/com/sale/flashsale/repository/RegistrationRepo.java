package com.sale.flashsale.repository;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.sale.flashsale.model.Registration;

@Repository
public class RegistrationRepo {

	@Autowired
	JdbcTemplate template;
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	
	public Registration getRegisterUser(Integer flashsaleId, Integer buyerId) {
		Registration cert=null;
		try {
            Object o = (Registration)template.queryForObject("select * from registrations where flashsaleid=? AND buyerid=?",
    				new Object[]{flashsaleId,buyerId},new BeanPropertyRowMapper<>(Registration.class));;
            cert = (Registration) o;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
		return cert;
	}

	public Integer registerBuyer(Integer flashsaleId, Integer buyerId, String regStatus) {
		
		 String query = "INSERT INTO REGISTRATIONS VALUES(?,?,?,?)";
		 Random rand = new Random();
		 int n = rand.nextInt(50);
	        return template.update(query,n,regStatus,flashsaleId,buyerId);
	}
}
	