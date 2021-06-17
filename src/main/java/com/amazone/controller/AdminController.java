package com.amazone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Admin;
import com.amazone.model.Product;
import com.amazone.services.AdminServices;
import com.amazone.services.ProductServices;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin("http://localhost:4200")
@RestController
@EnableSwagger2
@RequestMapping("/amazone-api/admin")
public class AdminController {

	@Autowired
	ProductServices productService;
	
	@Autowired
	AdminServices adminService;
	
	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value = "Admin Login Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Boolean> validateAdmin(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		System.out.println(username+"-"+password);
		boolean checkDetails = adminService.validateAdmin(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	@GetMapping("/admindetails/{adminid}")
	@ApiOperation(value = "Get Admin By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Admin> findAdminById(@PathVariable("adminid")String adminid) throws UserNotFoundException {
		System.out.println(adminid);
		Admin admin = adminService.getAdminById(adminid);
		System.out.println(admin);
		return ResponseEntity.ok(admin);
	}
	
	@PostMapping("/products")
	@ApiOperation(value = "Add Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> addProduct(@RequestBody Product product) throws IdNotFoundException {
		System.out.println(product);
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Added");
	}
	
	@PutMapping("/products")
	@ApiOperation(value = "Update Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> updateProduct(@RequestBody Product product) throws IdNotFoundException {
		productService.updateProduct(product);
		return ResponseEntity.accepted().body("Updated");
	}
	
	@DeleteMapping("/products/{productId}")
	@ApiOperation(value = "Delete Product Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int productId) throws IdNotFoundException {
		System.out.println(productId);
		productService.deleteProduct(productId);
		return ResponseEntity.accepted().body("Deleted");
	}
	
	@GetMapping("/products")
	@ApiOperation(value = "Show All Products Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findAllProducts() {
		List<Product> productList = productService.viewAllProduct();
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/products-randomly")
	@ApiOperation(value = "Show 10 Products Randomly Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findProductsRandomly() {
		List<Product> productList = productService.viewRandomlyTenProducts();
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/product-by-id/{productId}")
	@ApiOperation(value = "Get Product By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<Product> findProductById(@PathVariable("productId")int productId) throws IdNotFoundException {
		Product product = productService.viewProductById(productId);
		return ResponseEntity.ok(product);
	}
}
