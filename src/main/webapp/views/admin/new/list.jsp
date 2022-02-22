<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-news" />
<c:url var="NewURL" value="/admin-news" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value="/admin-news"/>" method="GET"
			id="formPaging">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<c:if test="${not empty message}">
				<div class="alert alert-${alert}">${message}</div>
				</c:if>
						<div class="col-xs-12">
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm bài viết'
												href='<c:url value="/admin-news?type=edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
											<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa bài viết'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>Đánh dấu</th>
													<th>Tên bài viết</th>
													<th>Mô tả ngắn</th>
													<th>Thao tác</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.list}">
													<c:url var="editURL" value="/admin-news">
														<c:param name="type" value="edit" />
														<c:param name="id" value="${item.id}" />
													</c:url>
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
														<td>${item.title}</td>
														<td>${item.shortDescription}</td>
														<td><a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																	title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																	</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page"> <input
											type="hidden" value="" id="pageItem" name="pageItem">
										<input type="hidden" value="" id="sortName" name="sortName">
										<input type="hidden" value="" id="sortBy" name="sortBy">
										<input type="hidden" value="" id="type" name="type">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- /.main-content -->
	<script type="text/javascript">
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				/* tổng số trang đang có */
				totalPages : totalPages,
				/* tổng số trang sẽ hiện để click vào */
				visiblePages : 2,
				/* trang bắt đầu hoặc đang đứng ở page nào */
				startPage : currentPage,
				/* khi nhấn vào 1 trang */
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$("#pageItem").val(limit);
						$("#page").val(page);
						$("#sortName").val('createddate');
						$("#sortBy").val("desc");
						$("#type").val("list");
						$("#formPaging").submit();
					}
				}
			});
		});
		
		$("#btnDelete").click(function() {
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
	            return $(this).val();
	        }).get();
			data['ids'] = ids;
			deleteNew(data);
		});
		
		
		function deleteNew(data){
			$.ajax({
				url: '${APIurl}',
				type: 'DELETE',
				contentType: 'application/json',
				data : JSON.stringify(data),
				dataType: 'json',
				success : function (result){
					window.location.href = "${NewURL}?page=${model.page}&pageItem=${model.pageItem}&sortName=createddate&sortBy=desc&type=list&alert=success&message=delete_success"
				},
				error : function (error){
					window.location.href = "${NewURL}?page=${model.page}&pageItem=${model.pageItem}&sortName=createddate&sortBy=desc&type=list&alert=danger&message=error_system"
				}
			})
		};
		
	</script>
</body>

</html>