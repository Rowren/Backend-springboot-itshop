package it.ecom.Itshop.service

import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.Model.OrderDetail
import it.ecom.Itshop.Model.User
import it.ecom.Itshop.repository.OrderRepository
import it.ecom.Itshop.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {

    /** ✅ สร้างออเดอร์ใหม่ */
    fun createOrder(orderDetails: List<Map<String, Any>>, user: User): ResponseEntity<Order> {
        val order = Order(
            user = user,
            total = 0.0, // คำนวณด้านล่าง
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        var totalPrice = 0.0
        val orderDetailList = mutableListOf<OrderDetail>()

        for (detail in orderDetails) {
            val productId = (detail["productId"] as Number).toInt()
            val quantity = (detail["quantity"] as Number).toInt()

            val product = productRepository.findById(productId)
                .orElseThrow { RuntimeException("Product ID: $productId not found") }

            val orderDetail = OrderDetail(
                order = order,
                product = product,
                quantity = quantity,
                price = product.price * quantity
            )

            orderDetailList.add(orderDetail)
            totalPrice += orderDetail.price
        }

        order.orderDetails = orderDetailList
        order.total = totalPrice

        return ResponseEntity.ok(orderRepository.save(order))
    }
}
