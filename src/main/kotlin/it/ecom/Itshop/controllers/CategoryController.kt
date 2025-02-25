package it.ecom.Itshop.controllers

import it.ecom.Itshop.services.CategoryService
import it.ecom.Itshop.Model.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): List<Category> = categoryService.getAllCategories()

    @GetMapping("/{id}")
    fun getCategory(@PathVariable id: Int): ResponseEntity<Category> {
        val category = categoryService.getCategoryById(id)
        return if (category.isPresent) {
            ResponseEntity.ok(category.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addCategory(@RequestBody category: Category): ResponseEntity<Category> {
        val savedCategory = categoryService.addCategory(category)
        return ResponseEntity.ok(savedCategory)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Int, @RequestBody category: Category): ResponseEntity<Category> {
        val updatedCategory = categoryService.updateCategory(id, category)
        return if (updatedCategory.isPresent) {
            ResponseEntity.ok(updatedCategory.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int): ResponseEntity<Category> {
        val deletedCategory = categoryService.deleteCategory(id)
        return if (deletedCategory.isPresent) {
            ResponseEntity.ok(deletedCategory.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
