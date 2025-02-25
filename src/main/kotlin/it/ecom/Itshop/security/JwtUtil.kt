package it.ecom.Itshop.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    private val secretKey = "mySecretKeymySecretKeymySecretKeymySecretKey" // ควรยาว >= 32 ตัวอักษร
    private val expirationMs = 86400000 // 24 ชั่วโมง

    fun generateToken(email: String): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray()) // ใช้ Key ที่ถูกต้อง
        return Jwts.builder()
            .subject(email) // ใช้ .subject() แทน .setSubject()
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(key, SignatureAlgorithm.HS256) // แก้ไขการเซ็นลายเซ็น
            .compact()
    }
}
