package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryReposutory:JpaRepository<Category,Int>{

}