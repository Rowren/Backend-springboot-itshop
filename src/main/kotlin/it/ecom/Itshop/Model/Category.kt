package it.ecom.Itshop.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(nullable = false, unique = true)
    var name: String = "",

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnoreProperties("category") // ✅ ป้องกัน loop
    var products: MutableList<Product> = mutableListOf()
)
