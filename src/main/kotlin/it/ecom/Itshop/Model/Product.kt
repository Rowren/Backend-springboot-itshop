package it.ecom.Itshop.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var name: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var qty: Int = 0,
    var sold: Int = 0,
    var productImage: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.EAGER) // ✅ โหลด Category มาพร้อมกับ Product
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products") // ✅ ป้องกัน loop
    var category: Category? = null
)
