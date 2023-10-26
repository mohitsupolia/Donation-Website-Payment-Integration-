// First request to server to create order
const paymentStart=()=>{
	let amount=$("#payment_field").val();
	console.log(amount);
	if(amount=='' || amount==null)
	{
	//	alert('Amount is required !!');
    swal({
		title:"Failed !!",
		text:"Amount is Required !!!",
		icon:"error",
		buttons: true,
		dangerMode: true,
	});
		return;
	}
//  code
//  we will use ajaex to send request to server to create order --jquery	
	$.ajax(
		{
			url:'/create_order',
			data:JSON.stringify({amount:amount,info:'order_request'}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response)
			{
		//   Invoked where success
				console.log(response);
				
				if(response.status=='created')
				{
		//    Open payment form...
		         let options={
					key:"rzp_test_RLtEAiP80t7O5Q",
					amount:response.amount,
					 currency:"INR",
					 name:"NGO",
		//			 image:"/images/NGO.png",
					 order_id:response.id,
					 handler:function(response){
						 console.log(response.razorpay_payment_id);
						 console.log(response.razorpay_order_id);
						 console.log(response.razorpay_signature);
						 console.log('Payment Successful');
				//		 alert("Congrates !! Payment Successful !!!");
				
				updatePaymentOnServer(response.razorpay_payment_id,response.razorpay_order_id,'paid');
				
		
					 },
					 
					 prefill: {
                        name: " ",
                        email: " ",
                        contact: " "
                     },
                       notes: {
                       address: "ABC",
                    },
                    theme: {
                       color: "#3399cc"
                   },
				 };	
				 
				 var rzp = new Razorpay(options);
                  rzp.on('payment.failed', function (response){
                  console.log(response.error.code);
                  console.log(response.error.description);
                  console.log(response.error.source);
                  console.log(response.error.step);
                  console.log(response.error.reason);
                  console.log(response.error.metadata.order_id);
                  console.log(response.error.metadata.payment_id);
        //          alert("Oops payment failed !!");
                  swal({
		      title:"Failed !!",
		      text:"Oops payment failed !!",
		      icon:"error",
		      buttons: true,
		      dangerMode: true,
	       });
               });
				 	rzp.open();
				 	
				}
				
			},
			error:function(error)
			{
		//   Invoked when error
		     console.log(error)
			 alert("Something went wrong !!!") 	
			},
			
		}
		
	)  ; 
};
//
    function updatePaymentOnServer(payment_id,order_id,status)
{
	$.ajax({
		url:'/update_order',
			data:JSON.stringify({payment_id:payment_id,order_id:order_id,status:status}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response){
				swal({
		      title:"Congrates !!",
		      text:"Congrates !! Payment Successful !!!",
		      icon:"success",
		      buttons: true,
		      dangerMode: true,
	       });
			},
			error:function(error){
				     swal({
		      title:"Failed !!",
		      text:"Your payment is successful, but we did not get on server, we will contact you as soon as possible !!",
		      icon:"error",
		      buttons: true,
		      dangerMode: true,
	       });
			},
	});
}
