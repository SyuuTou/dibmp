<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/storageaudit/storageaudit_list_history.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="4"/>
			<jsp:param name="msi" value="42"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;审核历史记录</strong>
			</div>
			<div class="panel-body">
				<div>
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_search_bar.jsp"/>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class="text-center" style="width:10%;">入库单编号 </th> 
							<th class="text-left" style="width:20%;">申请标题</th>  
							<th class="text-left" style="width:20%;">入库仓库</th>
							<th class="text-center" style="width:10%;">审核日期</th>
							<th class="text-center" style="width:10%;">提交人</th>
							<th class="text-center" style="width:10%;">商品数量</th>
							<th class="text-center" style="width:10%;">总价（￥）</th>
							<th class="text-center" style="width:10%;">审核人员</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${StorageApplyHistorys}" var="StorageApplyHistory">
						<tr>
							<th class="text-center" style="width:10%;">${StorageApplyHistory['singleStorageApply'].said}</th> 
							<td class="text-left"><span id="said-${StorageApplyHistory['singleStorageApply'].said}" style="cursor:pointer;">${StorageApplyHistory['singleStorageApply'].title}</span></td>
							<td class="text-left"><span id="wid-1" style="cursor:pointer;">也可调用仓储接口1:${StorageApplyHistory['warehouse'].name}<%-- ${StorageApplyHistory['warehouse'].name} --%></span></td>
							<td class="text-center"><fmt:formatDate value="${StorageApplyHistory['singleStorageApply'].checkdate}" pattern="yyyy-MM-dd" /></td>
							<td class="text-center"><span id="appmid_${StorageApplyHistory['appmember'].mid}" style="cursor:pointer;">${StorageApplyHistory['appmember'].name}</span></td>
							<td class="text-center">${StorageApplyHistory['allAmount']}</td>
							<td class="text-center">${StorageApplyHistory['totalPrice']}</td>
							<td class="text-center"><span id="checkmid_${StorageApplyHistory['auditmember'].mid}" style="cursor:pointer;">${StorageApplyHistory['auditmember'].name}</span></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div id="splitBarDiv" style="float:right">
					<jsp:include page="/WEB-INF/pages/plugins/split_plugin_page_bar.jsp"/> 
				</div>
			</div>
			<div class="panel-footer" style="height:100px;">
				<jsp:include page="/WEB-INF/pages/plugins/include_alert.jsp"/>
			</div>
		</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_info_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/storage_details_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/warehouse_info_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
