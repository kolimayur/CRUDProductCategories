package com.CrudApp.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CrudApp.demo.Models.Category;
import com.CrudApp.demo.Models.Product;
import com.CrudApp.demo.Repositories.CategoryRepository;
import com.CrudApp.demo.Repositories.ProductRepository;

@RestController
@RequestMapping("api")
public class CategoryProductController {

	@Autowired
	ProductRepository productRepo;		
	
	@Autowired
	CategoryRepository categoryRepo;
	
	
	/* ADD PRODUCT API */
	@PostMapping("product")
	public void addProduct(@RequestBody Product product, @Param(value = "page") int pg) 
	{
		productRepo.save(product);
	}
	
	/* GET ALL PRODUCT FULL DETAILS WITH CATEGORY API */
	@GetMapping("products")
	public List<Product> getAllProducts(@RequestParam(name = "page", required = false, defaultValue = "0") int pageNo)
	{
		return productRepo.findAll(PageRequest.of(pageNo,5)).toList();
	}

	/* GET PRODUCT BY ID API */
	@GetMapping("product/{id}")
	public Product getProductById(@PathVariable Long id)
	{
		return productRepo.getById(id);
	}
	
	/* UPDATE PRODUCT BY ID */
	@PutMapping("product/{id}")
	public Product updateProductById(@PathVariable Long id, @RequestParam(name = "product_name", required = false) String prodName)
	{
		 boolean isExist = categoryRepo.existsById(id);
		 if(isExist)
		 {
			 Product p = new Product();
			 p.setProductId(id);
			 p.setProductName(prodName);
			 return productRepo.saveAndFlush(p);
		 }
		 return null;
	}
	
	/* DELETE PRODUCT BY ID */
	@DeleteMapping("product/{id}")
	public String deleteProductById(@PathVariable Long id)
	{
		boolean isExist = productRepo.existsById(id);
		if (isExist)
		{
			productRepo.deleteById(id);
			return "Deleted Succefully";
		}
		return "id not found";
	}
	
	/* ADD CATEGORY API */
	@PostMapping("category")
	public void addCategory(@RequestBody Category category) 
	{
		categoryRepo.save(category);
	}
	
	/* GET ALL CATEGORIES  */
	@GetMapping("categories")
	public List<Category> getAllCategories(@RequestParam(name = "page",required = false,defaultValue = "0") int pageNo)
	{
		return categoryRepo.findAll(PageRequest.of(pageNo, 3)).toList();
	}
	
	/* GET CATEGORY BY ID */
	@GetMapping("category/{id}")
	public Category getCategoryById(@PathVariable Long id)
	{
		return categoryRepo.getById(id);
		
	}
	
	/* UPDATE CATEGORIES BY ID */
	@PutMapping("categories/{id}")
	public Category updateCategoryById(@PathVariable Long id, @RequestParam(name = "category_name", required = false) String catName)
	{
		 boolean isExist = categoryRepo.existsById(id);
		 if(isExist)
		 {
			 Category c = new Category();
			 c.setCategoryId(id);
			 c.setCategoryName(catName);
			 return categoryRepo.saveAndFlush(c);
		 }
		 return null;
	}
	
	/* DELETE CATEGORY BY ID */
	@DeleteMapping("category/{id}")
	public String deleteCategoryById(@PathVariable Long id)
	{
		boolean isExist = categoryRepo.existsById(id);
		if (isExist)
		{
			categoryRepo.deleteById(id);
			return "Deleted Succefully";
		}
		return "id not found";
	}
}

