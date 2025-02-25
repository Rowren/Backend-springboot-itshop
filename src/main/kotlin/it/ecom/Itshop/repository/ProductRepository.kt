package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product,Int> {

}