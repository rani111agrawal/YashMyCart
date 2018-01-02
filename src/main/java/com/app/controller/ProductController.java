package com.app.controller;

import com.app.model.Product;
import com.app.service.ProductService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController
{
  @Autowired
  private ProductService productService;

  @RequestMapping(value={"/product/"},method=RequestMethod.POST,produces = "application/json",consumes="application/json")
   public ResponseEntity<?> save(@RequestBody Product product) {
     long productId = productService.save(product);
     return ResponseEntity.ok().body(productId);
  }

  
  
  @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = "application/json")
   public ResponseEntity<Product> get(@PathVariable("productId") long productId) {
	  Product product = productService.get(productId);
     return ResponseEntity.ok().body(product);
  }

  
  @RequestMapping(value={"/product"}, method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity<List<Product>> list() {
     List<Product> products = productService.list();
     return ResponseEntity.ok().body(products);
  }

 
  @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT,produces = "application/json",consumes="application/json")
  public ResponseEntity<?> update(@PathVariable("productId") long productId, @RequestBody Product product) {
	  productService.update(productId, product);
     return ResponseEntity.ok().body("product has been updated successfully");
  }


  @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("productId") long productId) {
	  productService.delete(productId);
     return ResponseEntity.ok().body("product has been deleted successfully.");
  }
  
  
}