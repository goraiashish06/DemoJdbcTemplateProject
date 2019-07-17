package com.sale.flashsale.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.model.Registration;

@Repository
public class ProductRepository {
	@Autowired
	JdbcTemplate template;

	public Product getProductById(Integer productId) {
		Product cert = null;
		try {
			Object o = (Product) template.queryForObject("select * from product where productid=?",
					new Object[] { productId }, new BeanPropertyRowMapper<>(Product.class));
			;
			cert = (Product) o;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return cert;
	}

	public Integer saveProductDetailsForFlashSale(Product pr) {
		String query = "INSERT INTO Product VALUES(?,?,?,?,?)";
		Random rand = new Random();
		int n = rand.nextInt(50);
		return template.update(query, n, "Open", pr.getId(), pr.getName(), pr.getDescription(), pr.getPrice(),
				pr.getSku());
	}

	public List<Product> getAllProducts() {

		return template.query("select * from product", new ProductRowMapper());
	}

	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setSku(rs.getInt("sku"));
			return product;
		}
	}
}
