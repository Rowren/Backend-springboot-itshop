package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminOrderRepository : JpaRepository<Order, Int>
