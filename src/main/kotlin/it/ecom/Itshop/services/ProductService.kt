package it.ecom.Itshop.services

import it.ecom.Itshop.Model.Product
import it.ecom.Itshop.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {

    // Get all products
    fun getAllProducts(): List<Product> = productRepository.findAll()

    // Get product by id
    fun getProductById(id: Int): Optional<Product> {
        return productRepository.findById(id)
    }

    // Create product
    fun createProduct(product: Product): Product = productRepository.save(product)

    // Update product
    fun updateProduct(id: Int, updateProduct: Product): Product {
        return if (productRepository.existsById(id)) {
            updateProduct.id = id
            productRepository.save(updateProduct)
        } else {
            throw RuntimeException("Product not found with id: $id")
        }
    }

    // Delete product
    fun deleteProduct(id: Int) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
        } else {
            throw RuntimeException("Product not found with id: $id")
        }
    }

    // ✅ ฟังก์ชัน Filter สินค้าตามชื่อ, หมวดหมู่ และช่วงราคา
    fun getFilteredProducts(keyword: String?, category: String?, minPrice: Double?, maxPrice: Double?): List<Product> {
        return when {
            keyword != null && category != null && minPrice != null && maxPrice != null ->
                productRepository.findByNameContainingAndCategory_NameAndPriceBetween(keyword, category, minPrice, maxPrice)

            keyword != null -> productRepository.findByNameContaining(keyword)
            category != null -> productRepository.findByCategory_Name(category)
            minPrice != null && maxPrice != null -> productRepository.findByPriceBetween(minPrice, maxPrice)

            else -> productRepository.findAll()
        }
    }
}
