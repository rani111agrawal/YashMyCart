package com.app.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.app.model.Product;



@Repository("ProductDao")
public class ProductDaoImpl implements ProductDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	 public void setDataSource(DataSource dataSource) {
	  this.jdbcTemplate = new JdbcTemplate(dataSource);
	 }
	
	
	@Override
	public Product get(long productId) {
		
		Product product = null;
		  try {
			  product = jdbcTemplate.queryForObject("SELECT * FROM Product WHERE productId = ?",
		     new Object[] { productId }, new BeanPropertyRowMapper<Product>(Product.class));
		  } catch (DataAccessException e) {
		   e.printStackTrace();
		  }
		  return product;
	}
	
	
	@Override
	public List<Product> list() {
		List<Product> products = null ;
		try {
			products = jdbcTemplate.query("SELECT * FROM Product",new BeanPropertyRowMapper<Product>(Product.class));   
			  } catch (DataAccessException e) {
			   e.printStackTrace();
			  }
			  return products;
	}
	
	 @Override
		public long save(Product product) {
		 int count = jdbcTemplate.update(
				    "INSERT INTO Product(productId,createdAt, productName, updatedAt)VALUES(?,?,?,?)", new Object[] {
				    		product.getProductId(), product.getCreatedAt(), product.getProductName(), product.getUpdatedAt() });
		 return count;
		}


	@Override
	public void update(long productId, Product product) {
		int count = jdbcTemplate.update(
			    "UPDATE Product set createdAt = ? , productName = ? , updatedAt = ? where productId = ?", new Object[] {
			    		product.getCreatedAt(), product.getProductName(), product.getUpdatedAt(),productId });
		System.out.println("count>>>>> inside update"+count);
			
				
	}

	@Override
	public void delete(long productId) {
		int count = jdbcTemplate.update("DELETE from Product WHERE productId = ?", new Object[] { productId });
		
		
	}
	
	
}
