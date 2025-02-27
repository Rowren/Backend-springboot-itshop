package it.ecom.Itshop.controller

import it.ecom.Itshop.Model.User
import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    /** ✅ ดึงข้อมูล User */
    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // ❌ เช็คสิทธิ์
    fun getProfile(@AuthenticationPrincipal user: User): ResponseEntity<User> {
        return ResponseEntity.ok(user)
    }


    /** ✅ API สร้าง Order */
    @PostMapping("/orders")
    fun createOrder(
        @RequestBody orderDetails: List<Map<String, Any>>, // รับ Product ID และ Quantity
        principal: Principal
    ): ResponseEntity<Order> {
        return userService.createOrder(orderDetails, principal.name)
    }
}
