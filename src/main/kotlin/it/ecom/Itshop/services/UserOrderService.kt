package it.ecom.Itshop.services

import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserOrderService(private val orderRepository: OrderRepository) {

    // ✅ ดึงข้อมูลออเดอร์ทั้งหมด
    fun getAllOrders(): List<Order> = orderRepository.findAll()

    // ✅ ดึงข้อมูลออเดอร์ตาม ID
    fun getOrderById(orderId: Int): Optional<Order> = orderRepository.findById(orderId)

    // ✅ เพิ่มออเดอร์ใหม่
    fun createOrder(order: Order): Order {
        println("📌 Saving Order: $order")  // ✅ Debug Log
        order.orderDetails.forEach { it.order = order }
        return orderRepository.save(order)
    }
}
