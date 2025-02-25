package it.ecom.Itshop.controllers

import it.ecom.Itshop.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class AuthRequest(val email: String, val password: String)
data class AuthResponse(val token: String)

@RestController
@RequestMapping("api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequest): ResponseEntity<String> {
        val user = authService.register(request.email, request.password)
        return ResponseEntity.ok("User registered successfully: ${user.email}")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        val token = authService.login(request.email, request.password)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
