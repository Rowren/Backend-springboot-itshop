package it.ecom.Itshop.controllers

import it.ecom.Itshop.Model.User
import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.services.AdminUserService
import it.ecom.Itshop.services.AdminOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminUserService: AdminUserService,
    private val adminOrderService: AdminOrderService
) {

    // 📌 ✅ User Management APIs
    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(adminUserService.getAllUsers())
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<User> {
        val user = adminUserService.getUserById(id)
        return if (user.isPresent) ResponseEntity.ok(user.get()) else ResponseEntity.notFound().build()
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.status(201).body(adminUserService.saveUser(user))
    }

    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody updatedUser: User): ResponseEntity<User> {
        val existingUser = adminUserService.getUserById(id)
        return if (existingUser.isPresent) {
            val user = existingUser.get().apply {
                email = updatedUser.email
                password = updatedUser.password
                role = updatedUser.role
                address = updatedUser.address
                phone = updatedUser.phone
                updatedAt = updatedUser.updatedAt
            }
            ResponseEntity.ok(adminUserService.saveUser(user))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Void> {
        return try {
            adminUserService.deleteUser(id)
            ResponseEntity.noContent().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

    // 📌 ✅ Order Management APIs
    @GetMapping("/orders")
    fun getAllOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(adminOrderService.getAllOrders())
    }

    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable id: Int): ResponseEntity<Order> {
        val order = adminOrderService.getOrderById(id)
        return if (order.isPresent) ResponseEntity.ok(order.get()) else ResponseEntity.notFound().build()
    }

    @PutMapping("/orders/{id}")
    fun updateOrderStatus(@PathVariable id: Int, @RequestBody updatedOrder: Order): ResponseEntity<Order> {
        val existingOrder = adminOrderService.getOrderById(id)
        return if (existingOrder.isPresent) {
            val order = existingOrder.get().apply {
                status = updatedOrder.status
                paymentMethod = updatedOrder.paymentMethod
                shippedDate = updatedOrder.shippedDate
                updatedAt = updatedOrder.updatedAt
            }
            ResponseEntity.ok(adminOrderService.saveOrder(order))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/orders/{id}")
    fun deleteOrder(@PathVariable id: Int): ResponseEntity<Void> {
        return try {
            adminOrderService.deleteOrder(id)
            ResponseEntity.noContent().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}
