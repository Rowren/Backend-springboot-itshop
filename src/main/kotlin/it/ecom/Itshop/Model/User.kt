package it.ecom.Itshop.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(unique = true, nullable = false)
    var email: String = "",  // กำหนดให้เป็น unique และต้องไม่ null

    @Column(nullable = false)
    var password: String = "", // ควรเข้ารหัสก่อนบันทึก (ใช้ BCrypt)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER, // ใช้ Enum แทน String

    var address: String = "",
    var phone: String = "",

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var orders: MutableList<Order> = mutableListOf()
) {
    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}

enum class UserRole {
    USER, ADMIN
}
