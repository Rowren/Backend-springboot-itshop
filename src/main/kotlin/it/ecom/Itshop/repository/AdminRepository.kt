package it.ecom.Itshop.repository


import it.ecom.Itshop.Model.User
import org.springframework.data.jpa.repository.JpaRepository

interface AdminUserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User? // หา User จาก email
}
