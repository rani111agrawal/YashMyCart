package com.app.controller;

import com.app.controller.ProductController;
import com.app.model.Product;
import com.app.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.IOException;
import java.nio.charset.Charset;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/dispatcher-servlet.xml"})
public class ProductControllerTest
{
  @Mock
  private ProductService productService;
  private MockMvc mockMvc;
  
  @InjectMocks
  private ProductController productController;
  
  @Before
  public void setup() {

      
      MockitoAnnotations.initMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }
  Product product=new Product();
   
  @Test
  public void AddProductEntry_shouldAddEntryAndReturnAddedEntry() throws IOException, Exception
  {
	  Product product=new Product();
	  product.setProductId(1);
	  product.setCreatedAt("2016-03-17T10:28:06.524Z");
	  product.setProductName("Parmal");
	  product.setUpdatedAt("2016-03-17T10:28:06.524Z");
	  
	  when(productService.save(any(Product.class))).thenReturn(1L);
	  
	  mockMvc.perform(post("/product/")
			 .contentType( new MediaType(MediaType.APPLICATION_JSON.getType(),
                     MediaType.APPLICATION_JSON.getSubtype(),                        
                     Charset.forName("utf8")))
			 .content(convertObjectToJsonBytes(product))
		);
	 }
  
  @Test
  public void findByProductId_EntryFound_ShouldReturnFoundProductEntry() throws Exception {
	//  Product product=new Product();
	  product.setProductId(1);
	  product.setCreatedAt("2016-03-17T10:28:06.524Z");
	  product.setProductName("Parmal");
	  product.setUpdatedAt("2016-03-17T10:28:06.524Z");
	  
	  when(productService.get(1L)).thenReturn(product);
	  
	  mockMvc.perform(get("/product/{productId}", 1L))
              .andExpect(status().isOk())
              .andExpect(content().contentType("application/json;charset=UTF-8"));

      verify(productService, times(1)).get(1L);
      verifyNoMoreInteractions(productService);
  }
  
  @Test
  public void testUpdate() throws Exception {
      mockMvc.perform(
              put("/product/{productId}",1L)
                      .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                              MediaType.APPLICATION_JSON.getSubtype(),                        
                              Charset.forName("utf8")))
                      .content(convertObjectToJsonBytes(product)))
              .andExpect(MockMvcResultMatchers.status().isOk());
      when(productService.get(1L)).thenReturn(product);
              
  }
  
  @Test
  public void testDelete() throws Exception {
      mockMvc.perform(
              delete("/product/{productId}",1L))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
      when(productService.get(1L)).thenReturn(product);
  }
  
  public byte[] convertObjectToJsonBytes(Object object) throws IOException {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      return mapper.writeValueAsBytes(object);
  }
}