package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): Optional<User>
}
