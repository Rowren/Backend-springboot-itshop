package it.ecom.Itshop.services

import it.ecom.Itshop.Model.User
import it.ecom.Itshop.Model.UserRole
import it.ecom.Itshop.repository.UserRepository
import it.ecom.Itshop.security.JwtUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun register(email: String, password: String, role: UserRole?): User {
        if (userRepository.findByEmail(email).isPresent) {
            throw RuntimeException("Email already registered")
        }

        val hashedPassword = passwordEncoder.encode(password)
        val userRole = role ?: UserRole.USER // ใช้ค่า role ที่ส่งมา หรือเป็น USER ถ้าไม่มี

        val user = User(email = email, password = hashedPassword, role = userRole)
        return userRepository.save(user)
    }



    fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email)
            .orElseThrow { RuntimeException("User not found") }

        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("Invalid password")
        }

        return jwtUtil.generateToken(email) // ใช้ email เป็น subject ของ JWT
    }
}
