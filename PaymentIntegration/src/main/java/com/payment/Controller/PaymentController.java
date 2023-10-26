package com.payment.Controller;



import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payment.Repository.MyOrderRepository;
import com.payment.entities.MyOrder;
import com.razorpay.*;;

@Controller
public class PaymentController
{
	@Autowired(required=true)
	private MyOrderRepository myOrderRepository;
	
	@RequestMapping("/payment")
	public String payment(Model model)
	{
		model.addAttribute("title","Payment Form");
		return "dash_board";
	}
//  creating order for payment
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String,Object> data) throws Exception
	{
		System.out.println(data);
		int amt=Integer.parseInt(data.get("amount").toString());
		var client=new RazorpayClient("rzp_test_RLtEAiP80t7O5Q","U62M4yvrJkJaXWNzYudP6OGT");
		
		JSONObject ob=new JSONObject();
		ob.put("amount", amt*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		
//      creating new order...(request goes to third party)		
		Order order = client.Orders.create(ob);
		System.out.println(order);
//     Save the order in database..
		MyOrder myOrder=new MyOrder();
		myOrder.setAmount(order.get("amount")+"");
		myOrder.setOrderId(order.get("id"));
		myOrder.setPaymentId(null);
		myOrder.setStatus("created");
		myOrder.setReceipt(order.get("receipt"));
		this.myOrderRepository.save(myOrder);
		
		return order.toString();
	}
//  
	@PostMapping("/update_order")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String,Object> data)
	{
		MyOrder myorder=this.myOrderRepository.findByOrderId(data.get("order_id").toString());
		myorder.setPaymentId(data.get("order_id").toString());
		myorder.setStatus(data.get("status").toString());
		this.myOrderRepository.save(myorder);
		System.out.println(data);
		return ResponseEntity.ok(Map.of("message","Updated"));
	}
}
