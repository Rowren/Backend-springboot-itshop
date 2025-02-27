package it.ecom.Itshop.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "order_details")
@JsonIgnoreProperties("order")  // ✅ ป้องกัน Recursion เวลาส่ง JSON กลับ
data class OrderDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var quantity: Int = 1,
    var price: Double = 0.0,

    @ManyToOne(fetch = FetchType.EAGER)  // ✅ โหลดข้อมูลพร้อมกัน
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var product: Product? = null

)
