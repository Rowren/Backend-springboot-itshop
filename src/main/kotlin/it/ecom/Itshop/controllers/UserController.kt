package it.ecom.Itshop.controller

import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.services.UserOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class OrderController(private val userOrderService: UserOrderService) {

    @GetMapping("/orders")
    fun getAllOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(userOrderService.getAllOrders())
    }

    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable id: Int): ResponseEntity<Order> {
        return userOrderService.getOrderById(id)
            .map { ResponseEntity.ok(it) }
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @PostMapping("/orders")
    fun createOrder(@RequestBody order: Order): ResponseEntity<Order> {
        println("📌 Receiving Order: $order")  // ✅ Debug Log
        val newOrder = userOrderService.createOrder(order)
        return ResponseEntity.ok(newOrder)
    }
}
