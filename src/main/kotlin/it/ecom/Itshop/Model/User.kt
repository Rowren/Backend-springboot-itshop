package it.ecom.Itshop.Model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users") // เพิ่ม Entity ให้ถูกต้อง
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(unique = true, nullable = false)
    var email: String = "",  // ใช้ email แทน username

    var password: String = "",
    var role: String = "USER",
    var address: String = "",
    var phone: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orders: MutableList<Order> = mutableListOf() // ใช้ MutableList
)

