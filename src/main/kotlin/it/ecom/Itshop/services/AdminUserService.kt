package it.ecom.Itshop.services


import it.ecom.Itshop.Model.User
import it.ecom.Itshop.repository.AdminUserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdminUserService(private val adminUserRepository: AdminUserRepository) {

    // ดึงข้อมูลผู้ใช้ทั้งหมด
    fun getAllUsers(): List<User> = adminUserRepository.findAll()

    // ดึงข้อมูลผู้ใช้ตาม ID
    fun getUserById(id: Int): Optional<User> = adminUserRepository.findById(id)

    // เพิ่มหรืออัปเดตข้อมูลผู้ใช้
    fun saveUser(user: User): User = adminUserRepository.save(user)

    // ลบผู้ใช้ตาม ID
    fun deleteUser(id: Int) {
        if (adminUserRepository.existsById(id)) {
            adminUserRepository.deleteById(id)
        } else {
            throw IllegalArgumentException("User ID $id not found")
        }
    }


}
