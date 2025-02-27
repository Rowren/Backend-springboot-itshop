package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Int>{

}
