package com.payment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.entities.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long>
{
	public MyOrder findByOrderId(String orderId);
}
