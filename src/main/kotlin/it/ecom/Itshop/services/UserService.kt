package it.ecom.Itshop.service

import it.ecom.Itshop.Model.User
import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val orderService: OrderService // เรียกใช้ OrderService
) {

    /** ✅ ดึงข้อมูล User */
    fun getUserProfile(email: String): ResponseEntity<User> {
        val user = userRepository.findByEmail(email)
            .orElseThrow  { RuntimeException("User not found") }
        return ResponseEntity.ok(user)
    }

    /** ✅ หา User จาก Email */
    fun findByEmail(email: String): User {
        return userRepository.findByEmail(email)
            .orElseThrow { RuntimeException("User not found") }
    }

    /** ✅ API เรียกใช้ OrderService สร้าง Order */
    fun createOrder(orderDetails: List<Map<String, Any>>, email: String): ResponseEntity<Order> {
        val user = findByEmail(email)
        return orderService.createOrder(orderDetails, user)
    }
}
