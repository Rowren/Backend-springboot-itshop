package it.ecom.Itshop.services

import it.ecom.Itshop.Model.Order
import it.ecom.Itshop.repository.AdminOrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdminOrderService(private val adminOrderRepository: AdminOrderRepository) {

    fun getAllOrders(): List<Order> = adminOrderRepository.findAll()

    fun getOrderById(id: Int): Optional<Order> = adminOrderRepository.findById(id)

    fun saveOrder(order: Order): Order = adminOrderRepository.save(order)

    fun deleteOrder(id: Int) {
        if (adminOrderRepository.existsById(id)) {
            adminOrderRepository.deleteById(id)
        } else {
            throw IllegalArgumentException("Order ID $id not found")
        }
    }
}
