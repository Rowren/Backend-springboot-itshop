package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int> {

    // 🔍 ค้นหาสินค้าจากชื่อ (name) ที่มีคำที่กำหนด
    fun findByNameContaining(keyword: String): List<Product>

    // 🔍 ค้นหาสินค้าจากราคาขั้นต่ำและสูงสุด
    fun findByPriceBetween(minPrice: Double, maxPrice: Double): List<Product>

    // 🔍 ค้นหาสินค้าตามหมวดหมู่ (ใช้ category.name)
    fun findByCategory_Name(category: String): List<Product>

    // 🔍 ค้นหาทั้งชื่อสินค้า + ประเภท + ราคาตามช่วง
    fun findByNameContainingAndCategory_NameAndPriceBetween(
        keyword: String,
        category: String,
        minPrice: Double,
        maxPrice: Double
    ): List<Product>
}
