$(function(){
	$("span[id^=mid-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#memberInfo").modal("toggle") ;
		}) ;
	}) ;
	$("span[id^=said-]").each(function(){
		$(this).on("click",function(){
			sid = this.id.split("-")[1] ;
			$("#storageDetailsInfo").modal("toggle") ;
		}) ;
	}) ;
	$("span[id^=wid-]").each(function(){
		$(this).on("click",function(){
			sid = this.id.split("-")[1] ;
			$("#warehouseInfo").modal("toggle") ;
		}) ;
	}) ;
}) ;
