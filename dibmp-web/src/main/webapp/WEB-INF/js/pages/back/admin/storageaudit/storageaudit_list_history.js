$(function(){
	
	$("span[id^=wid-]").each(function(){
		$(this).on("click",function(){
			wid = this.id.split("-")[1] ;
			console.log(wid);
			$("#warehouseInfo").modal("toggle") ;
			$.ajax({
				method:"post",
				url:"pages/back/admin/storageaudit/list_warehouseInfo.action",
				data:{
					"wid":wid
				},
				dataType:"json",
				success:function(data){
					$("#wname").text(data.selWarehouse.name);
					$("#address").text(data.selWarehouse.address);
					$("#wiid").text(data.selWitem.wiid);
					$("#maximum").text(data.selWarehouse.maximum);
					$("#currnum").text(data.selWarehouse.currnum);
					$("#warhousenote").text(data.selWarehouse.note);
				},
				error:function(){
					console.log("错误，赶紧找找错误去")
				}
				
			});
		}) ;
	}) ;
	
	$("span[id^=appmid_]").each(function(){
		$(this).on("click",function(){
			var mid = this.id.split("_")[1] ;
			console.log(mid);
			
			$("#memberInfo").modal("toggle") ;
			$.ajax({
				method:"post",
//				url:"storage.audit.list.memberInfo.action",
				url:"pages/back/admin/storageaudit/list_memberInfo.action",
				data:{
					"mid" : mid
				},
				dataType:"json",
				success:function(data){
					console.log(data);
					$("#mname").text(data.selMember.name);
					$("#title").text(data.selLevel.title);
					$("#dname").text(data.selDept.dname);
					$("#phone").text(data.selMember.phone);
					$("#note").text(data.selMember.note);
				},
				error:function(){
					console.log("错误的提示！");
				}
			});
		}) ;
	}) ;
	$("span[id^=checkmid_]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("_")[1] ;
			console.log(mid);
			
			$("#memberInfo").modal("toggle") ;
			$.ajax({
				method:"post",
//				url:"storage.audit.list.memberInfo.action",
				url:"pages/back/admin/storageaudit/list_memberInfo.action",
				data:{	
					"mid" : mid
				},
				dataType:"json",
				success:function(data){
					console.log(data);
					$("#mname").text(data.selMember.name);
					$("#title").text(data.selLevel.title);
					$("#dname").text(data.selDept.dname);
					$("#phone").text(data.selMember.phone);
					$("#note").text(data.selMember.note);
				},
				error:function(){
					console.log("错误的提示！");
				}
			});
		}) ;
	}) ;
	$("span[id^=said-]").each(function(){
		$(this).on("click",function(){
			said = this.id.split("-")[1] ;
			$("#storageDetailsInfo").modal("toggle") ;
			loadData();
		}) ;
	}) ;
	
}) ;
function loadData() {
	var ls =  jsCommonCp;
	var cp = jsCommonLs;
	$.ajax({
		method:"post",
		url:"pages/back/admin/storageaudit/list_storageDetailsInfo.action",
		data:{
			"currentPage":ls,
			"lineSize":cp,
			"said":said
		},
		dataType:"json",
		success:function(data){
			console.log(data);
			$("#title-tian").text(data.selectStorageApply.title);
			$("#wid-tian").text(data.selectWarehouse.name);
			$("#wiid-tian").text(data.selectWitem.title);
			$("#allprice-tian").text("￥"+data.totalPrice);
			$("#note-tian").text(data.selectStorageApply.note);
			console.log(data.selectStorageApplyDetails.length);
			$("#sbtiger").empty();
			for(x=0;x<data.selectStorageApplyDetails.length;x++){
			row = 	"<tr class='text-primary' id='"+x+"'>"+
						"<td class='text-center'>"+data.selectStorageApplyDetails[x].gid+"</td>"+
						"<td class='text-left'>"+data.selectStorageApplyDetails[x].name+"</td>"+
						"<td class='text-center'>"+data.selectStorageApplyDetails[x].num+"</td>"+
						"<td class='text-center'>"+data.selectStorageApplyDetails[x].price+"</td>"+
						"<td class='text-center'>"+data.selectStorageApplyDetails[x].weight+"</td>"+
						"<td id='"+x+"' class='text-center'>"+data.selectStorageApplyDetails[x].num*data.selectStorageApplyDetails[x].price+"</td>"+
					"</tr>";
			$("#sbtiger").append($(row));}
			console.log(data);
			createSplitBar(data.allRecorders) ;//创建分页控制，前面没有写分页无法实现的
		},
		error:function(){
			console.log("错误，赶紧找找错误去");
		}
	});
}