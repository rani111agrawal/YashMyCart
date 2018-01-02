package com.app.service;

import java.util.List;

import com.app.model.Product;

public interface ProductService
{
	 long save(Product product);
	 Product get(long productId);
	   List<Product> list();
	   void update(long productId, Product product);
	   void delete(long productId);
}