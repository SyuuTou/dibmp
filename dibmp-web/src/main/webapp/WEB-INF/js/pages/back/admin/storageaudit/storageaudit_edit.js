$(function(){
	$("#showMember").on("click",function(){
		$("#memberInfo").modal("toggle") ;
	}) ;
	$("#showWarehouse").on("click",function(){
		$("#warehouseInfo").modal("toggle") ;
	}) ;
	/*$("#addBtn").on("click",function(){
		console.log("****************88");
		var audit=$("#audit").val();
		var note=$("#note").val();
		console.log(audit);
		console.log(note);
		if(audit!="" && note!=""){
			$.ajax({
				method:"post",
				url:"pages/back/admin/storageaudit/edit.action",
				data:{
					"audit": audit,
					"note" : note
				},
				dataType:"txt",
				success:function(data){
					console.log(data);
					//$("#mname").text(data.selMember.name);
					operateAlert(data,"审核通过！","审核不通过！") ;
				},
				error:function(){
					console.log("错误的提示！");
				}
			});
		}else{
			console.log("审核备注信息输入为空！");
			operateAlert(false,"Anything is ok","请输入审核备注信息！") ;
		}
	})*/
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"audit" : {
				required : true,
			},
			"note" : {
				required : true
			}
		}
	});
})