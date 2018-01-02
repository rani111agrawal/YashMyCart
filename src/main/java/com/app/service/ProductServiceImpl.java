package com.app.service;


import com.app.dao.ProductDao;
import com.app.model.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService
{

	
	ProductDao productDao;
	
	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Transactional
	@Override
	public long save(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public Product get(long productId) {
		
		return productDao.get(productId);
	}

	@Override
	public List<Product> list() {
		
		return productDao.list();
	}
	
	@Override
	public void update(long productId, Product product) {
	productDao.update(productId, product);	
		
	}
	
	@Override
	public void delete(long productId) {
	productDao.delete(productId);	
		
	}


}
