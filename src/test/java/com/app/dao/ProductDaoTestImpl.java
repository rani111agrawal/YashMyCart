package com.app.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.dao.ProductDao;
import com.app.dao.ProductDaoImpl;
import com.app.model.Product;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/dispatcher-servlet.xml"})
public class ProductDaoTestImpl {

	 @Mock
	 ProductDao productDao;
	 @InjectMocks
	 ProductDaoImpl productDaoImpl;
	 
	 
	@Before
	  public void setupMock() throws Exception{
	      MockitoAnnotations.initMocks(this);
	      
	  }
			
	@Ignore
	@Test
	public void testDummy() {
	
		assertNull(null);
	}
	
		
	@Test  
    public void testGetProductById() {  
		Product product = new Product();
		product.setProductId(1L);
		product.setCreatedAt("2016-03-17T10:28:06.524Z");
		product.setProductName("Äloo Bhujiya");
		product.setUpdatedAt("2016-03-17T10:28:06.524Z");
		
			
		product = productDao.get(1L);
		assertEquals(this.productDao.get(1L), product);
		
		
		
			
    } 
	
	
}
