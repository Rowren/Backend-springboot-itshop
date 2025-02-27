package it.ecom.Itshop.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(nullable = false)
    var total: Double = 0.0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus = OrderStatus.PENDING, // ใช้ Enum

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var paymentMethod: PaymentMethod = PaymentMethod.CASH, // ใช้ Enum

    var shippedDate: LocalDateTime? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var orderDetails: MutableList<OrderDetail> = mutableListOf()
) {
    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}

enum class OrderStatus {
    PENDING, PAID, SHIPPED, CANCELED
}

enum class PaymentMethod {
    CASH, CREDIT_CARD, PAYPAL
}
