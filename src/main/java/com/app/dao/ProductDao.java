package com.app.dao;

import java.util.List;

import com.app.model.Product;

public interface ProductDao {
	
	long save(Product product);
	Product get(long productId);
	List<Product> list();
	void update(long productId, Product product);
	void delete(long productId);

}
