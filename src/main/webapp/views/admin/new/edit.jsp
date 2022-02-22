<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-news" />
<c:url var="NewURL" value="/admin-news" />
<html>

<head>
	<title>Chỉnh sửa bài viết</title>
</head>

<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {}
				</script>
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li class="active">Chỉnh sửa bài viết</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<c:if test="${not empty message}">
				<div class="alert alert-${alert}">${message}</div>
				</c:if>
					<div class="col-xs-12">
						<form id="formSubmit">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Thể
									Loại</label>
								<div class="col-sm-9">
									<select class="form-control" id="categoryCode" name="categoryCode" tabindex="0">
										<option value="">CHỌN THỂ LOẠI BÀI VIẾT</option>
										<c:forEach var="item" items="${categories}">

											<option value="${item.code}" <c:if
												test="${model.categoryCode == item.code}">selected="selected"</c:if>>
												${item.name}</option>
										</c:forEach>

									</select>
								</div>
							</div>
							<br /><br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Tiêu
									đề</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="title" name="title"
										value="${model.title}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Hình
									đại diện</label>
								<div class="col-sm-9">
									<input type="file" id="input_img" onchange="fileChange()" accept="image/*">
									<input type="hidden" class="form-control" id="thumbnail" name="thumbnail"
										value="${model.thumbnail}" />
								</div>
								<img alt="" src="${model.thumbnail}" width="200" height="200">
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Mô
									tả ngắn</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="shortDescription"
										name="shortDescription" value="${model.shortDescription}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Nội
									dung</label>
								<div class="col-sm-9">
									<textarea rows="" cols="" id="content" name="content"
										style="width: 820px; height: 175px">${model.content}</textarea>
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${not empty model.id}">
										<input type="button" class="btn  btn-warning btn-bold" value="Cập nhật bài viết"
											id="btnAddOrUpdateNew" />
									</c:if>
									<c:if test="${empty model.id}">
										<input type="button" class="btn btn-info btn-bold" value="Thêm mới bài viết"
											id="btnAddOrUpdateNew" />
										<input type="reset" class="btn  btn-success btn-bold" value="Reset" />
									</c:if>
								</div>
							</div>
							<input type="hidden" value="${model.id}" id="id" name="id" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		var editor = '';
		$(document).ready(function () {
			editor = CKEDITOR.replace('content');
		});

		function fileChange() {
			var file = document.getElementById('input_img');
			var form = new FormData();
			form.append("image", file.files[0])

			var settings = {
				"url": "https://api.imgbb.com/1/upload?key=64ce8ec45736c6f0961f04c071ea8ab2",
				"method": "POST",
				"timeout": 0,
				"processData": false,
				"mimeType": "multipart/form-data",
				"contentType": false,
				"data": form
			};


			$.ajax(settings).done(function (response) {
				console.log(response);
				var jx = JSON.parse(response);
				console.log(jx.data.url);
				$("#thumbnail").val(jx.data.url);
			});
		};

		$("#btnAddOrUpdateNew").click(function (e) {
			e.preventDefault();
			var data = {};
			var formData = $("#formSubmit").serializeArray();
			$.each(formData, function (i, v) {
				data["" + v.name + ""] = v.value;
				console.log(data);
			});
			data["content"] = editor.getData();
			var id = $("#id").val();
			if (id == "") {
				addNew(data);
			} else {
				updateNew(data);
			}


		});

		function addNew(data) {
			$.ajax({
				url: '${APIurl}',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function (result) {
					window.location.href = "${NewURL}?type=edit&id="+result.id
				},
				error: function (error) {
					console.log(error);
				}
			})
		};

		function updateNew(data) {
			$.ajax({
				url: '${APIurl}',
				type: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function (result) {
					window.location.href = "${NewURL}?type=edit&id="+result.id
				},
				error: function (error) {
					console.log(error);
				}
			})
		};
	</script>

</body>

</html>