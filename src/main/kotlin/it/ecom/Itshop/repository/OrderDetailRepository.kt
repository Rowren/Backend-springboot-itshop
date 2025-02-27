package it.ecom.Itshop.repository

import it.ecom.Itshop.Model.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository

interface OrderDetailRepository : JpaRepository<OrderDetail, Int>
