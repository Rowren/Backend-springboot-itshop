package it.ecom.Itshop.services
import it.ecom.Itshop.Model.Category
import it.ecom.Itshop.repository.CategoryReposutory
import it.ecom.Itshop.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService(private val categoryRepository: CategoryReposutory) {

    // Get all categories
    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    // Get category by id
    fun getCategoryById(id: Int): Optional<Category> {
        return categoryRepository.findById(id)
    }

    // Create category
    fun addCategory(category: Category): Category {
        return categoryRepository.save(category)
    }

    // Update category
    fun updateCategory(id: Int, category: Category): Optional<Category> {
        val existingCategory = categoryRepository.findById(id)
        return if (existingCategory.isPresent) {
            val cat = existingCategory.get()
            cat.name = category.name
            Optional.of(categoryRepository.save(cat))
        } else {
            Optional.empty()
        }
    }

    // Delete category
    fun deleteCategory(id: Int): Optional<Category> {
        val existingCategory = categoryRepository.findById(id)
        return if (existingCategory.isPresent) {
            categoryRepository.delete(existingCategory.get())
            existingCategory
        } else {
            Optional.empty()
        }
    }

}