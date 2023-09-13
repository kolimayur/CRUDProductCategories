package com.CrudApp.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.CrudApp.demo.Models.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long>{

}
