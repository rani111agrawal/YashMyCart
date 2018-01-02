package com.app.service;


import com.app.dao.ProductDao;
import com.app.model.Product;
import com.app.service.ProductServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/dispatcher-servlet.xml"})
public class ProductServiceImplTest
{
	@InjectMocks
    private ProductServiceImpl productServiceImpl;
  
	@Mock
	private Product product;
	@Mock
	ProductDao productDao;
	
	
  @Before
  public void setupMock() {
      MockitoAnnotations.initMocks(this);
  }
 
  @Test
  public void testGetProduct(){
	  
	  when(product.getProductId()).thenReturn(1L);
	  when(product.getCreatedAt()).thenReturn("2016-03-17T10:28:06.524Z");
	  when(product.getProductName()).thenReturn("Parmal");
	  when(product.getUpdatedAt()).thenReturn("2016-03-17T10:28:06.524Z");
	  verifyNoMoreInteractions(product);
	 }
  
  @Test
  public void testSaveProduct(){
	  
	  when(productDao.save(any(Product.class))).thenReturn(1L);
	  
	  assertNotNull(productDao);
	}
  
  @SuppressWarnings("unchecked")
@Test(expected = RuntimeException.class)
  public void testSaveProduct_throwsException() {

      when(productDao.save(any(Product.class))).thenThrow(RuntimeException.class);

      Product product = new Product();

      productServiceImpl.save(product);
     
  }
  
  @Test
  public void testListOfAllProduct(){
	  
	  when(productDao.list()).thenReturn(Arrays.asList(new Product()));
	  assertNotNull(productDao);
  }
  
  @Test
  public void testUpdateProduct(){
	  
	  
	  when(productDao.get(1L)).thenReturn(new Product());
	  productServiceImpl.update(1L, product);
	  verify(productDao,times(1)).update(1L, product);
	  assertNotNull(product.getProductId());
	
	}
  
    
  @Test
  public void testDeleteProduct() throws Exception{
	  
	  when(productDao.get(1L)).thenReturn(new Product());
	  productServiceImpl.delete(1L);
	  verify(productDao,times(1)).delete(1L);
	  
	}
  
 
	
		
		
}