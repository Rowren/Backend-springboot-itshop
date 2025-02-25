package it.ecom.Itshop.controllers

import it.ecom.Itshop.Model.Product
import it.ecom.Itshop.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts() = productService.getAllProducts()


    @GetMapping("/{id}")
    fun getProductById(@PathVariable id:Int):ResponseEntity<Product>{
        val product = productService.getProductById(id)
        return product.map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    // Create product
    @PostMapping
    fun createProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val createdProduct = productService.createProduct(product)
        return ResponseEntity(createdProduct, HttpStatus.CREATED)
    }

    // Update Product
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Int, @RequestBody product: Product): ResponseEntity<Product> {
        val updatedProduct = productService.updateProduct(id, product)
        return ResponseEntity.ok(updatedProduct)
    }

    // Delete Product
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }



}