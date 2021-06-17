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

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.IdNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Cart;
import com.amazone.model.Product;
import com.amazone.model.User;
import com.amazone.services.CartService;
import com.amazone.services.ProductServices;
import com.amazone.services.UserServices;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin("http://localhost:4200")
@RestController
@EnableSwagger2
@RequestMapping("/amazone-api/user")
public class UserController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	ProductServices productServices;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/login/{username}/{password}")
	@ApiOperation(value = "Login Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Boolean> validateUser(@PathVariable("username")String username, @PathVariable("password")String password) throws UserNotFoundException {
		boolean checkDetails = userServices.validateUser(username, password);
		return ResponseEntity.ok(checkDetails);
	}
	
	@PostMapping("/register")
	@ApiOperation(value = "Register User Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> registerUser(@RequestBody User user) throws UserAlreadyExistException {
		System.out.println(user);
		userServices.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered");
	}
	
	@GetMapping("/userdetail/{userid}")
	@ApiOperation(value = "Get User By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<User> findUserById(@PathVariable("userid")String userid) throws UserNotFoundException {
		System.out.println(userid);
		User user = userServices.getUserById(userid);
		System.out.println(user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/products")
	@ApiOperation(value = "Show All Products Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findAllProducts() {
		List<Product> productList = userServices.viewAllProducts();
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/product-by-id/{productid}")
	@ApiOperation(value = "Get Product By Id Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Product> findProductById(@PathVariable("productid")int productid) throws IdNotFoundException {
		Product product = productServices.viewProductById(productid);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/products-by-price/{price}")
	@ApiOperation(value = "Get Products By Price Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findProductByPrice(@PathVariable("price")String price) throws ProductNotFoundException {
		System.out.println(price);
		List<Product> productList =  userServices.ViewProductByPrice(price);
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/sort-products/{choice}")
	@ApiOperation(value = "Get Products By Price Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> sortProductByPrice(@PathVariable("choice")String choice){
		System.out.println(choice);
		List<Product> productList =  userServices.sortProducts(choice);
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/products-by-brand/{brand}")
	@ApiOperation(value = "Get Products By Brand Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findProductByBrand(@PathVariable("brand")String brand) throws BrandNotFoundException {
		List<Product> productList =  userServices.ViewProductByBrand(brand);
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/products-by-category/{category}")
	@ApiOperation(value = "Get Products By Category Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> findProductByCategory(@PathVariable("category")String category) throws CategoryNotFoundException {
		List<Product> productList = userServices.viewProductByCategory(category);
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/products-by-choice/{choice}")
	@ApiOperation(value = "Get Products By Choice Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Product>> getByCategoryOrTitleOrAuth(@PathVariable("choice")String choice) throws CategoryNotFoundException {
		List<Product> productList = userServices.ViewProductByNameOrBrandOrCategory(choice);
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/cart")
	@ApiOperation(value = "Show All Products in Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<List<Cart>> findAllCartProducts() {
		List<Cart> cartList = cartService.allCartItems();
		return ResponseEntity.ok(cartList);
	}
	
	@PostMapping("/cart")
	@ApiOperation(value = "Add Product in Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<String> addProductInCart(@RequestBody Cart cart) throws IdNotFoundException {
		System.out.println(cart);
		cartService.addProductToCart(cart);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Inside Cart");
	}
	
	@DeleteMapping("/cart/{productId}")
	@ApiOperation(value = "Delete Product From Cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int productId) throws IdNotFoundException {
		System.out.println(productId);
		cartService.deleteProductFromCart(productId);
		return ResponseEntity.accepted().body("Removed From Cart");
	}
	
	@GetMapping("/cartTotal")
	@ApiOperation(value = "Get Cart Total Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	ResponseEntity<Integer> getTotalCartPrice() {
		int total = cartService.cartTotal();
		return ResponseEntity.ok(total);
	}
	
	@DeleteMapping("/delete-all")
	@ApiOperation(value = "Delete All Product From cart Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> deleteAllCartProduct(){
		cartService.deleteAll();
		return ResponseEntity.accepted().body("Deleted");
	}
	
	@PutMapping("/wallet-balance")
	@ApiOperation(value = "Update Wallet Operation", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message= "Success"),
			@ApiResponse(code = 401, message= "Message Not Found")
	})
	public ResponseEntity<String> updateWalletBalance(@RequestBody User user) throws IdNotFoundException {
		userServices.updateWalletBalance(user);
		return ResponseEntity.accepted().body("Updated");
	}
	
}
