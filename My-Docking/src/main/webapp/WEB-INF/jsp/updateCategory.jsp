<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
      $(function(){
         $(':submit').click(function(){
              $('select').each(function(){
                  if ( $(this).val() == '' )
                  {
                      $(this).attr('disabled','disabled');// or $(this).remove();
                  }
              });
         });
    });
</script>

<meta charset="ISO-8859-1">
<title>Update Kategorie | My Docking</title>
<%@ include file="header.jsp"%>

<div class="container">
	<div class="row content">
		<form:form modelAttribute="category" action="/web/updateCategory"
			method="POST" class="form-horizontal" enctype = "multipart/form-data">

			<input type="hidden" name="id" value="${id}" />

			<%-- <div class="form-group row">
				<label class="col-sm-2 control-label"> Title :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="title"
						value="${title}">
				</div>
			</div> --%>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Kategorie Name :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="name"
						value="${name}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Elternteil Kategorie :</label>
				<div class="col-sm-4">
					<select name="parentCategory" class="form-control ">
						<option value="0">wählen Kategorie</option>
						<c:forEach items="${categories}" var="category">
							<option	${category.id == parentCategory ? 'selected="selected"' : ''}
								value="${category.id}">${category.name} &lt- ${category.parentCategory.name!=null ? category.parentCategory.name : 'N.A.'}  &lt- ${category.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.parentCategory.name : 'N.A.' }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<%-- <div class="form-group row">
				<label class="col-sm-2 control-label"> bearbeiten :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="description"
						value="${description}">
				</div>
			</div> --%>
			
			<input type="hidden" id="iconName" name="iconName" value="${icon}">
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Kategorie Ikone :</label>
					<div class="col-sm-4">
					<table border="1" class="table table-striped table-bordered">
						<tr>
							<td>
								<div class="col-sm-12">
									<input type="file" class="form-control" name="icon1" id="upload"/>
									<span style="color: tomato;">Empfohlende Pixelgrösse 879px X 329px</span>
								</div>
							</td>
						</tr>
						<c:if test = "${icon != null}">
						<tr>
							<td>
								<div class="col-sm-12">
									<table border="1" id="table" style="width: inherit;">
											<tbody>
												<tr id="iconIndex">
													<td style="padding: 5px"><a href="/api/downloadFile/${icon}">${icon}</a></td>
													<td style="padding: 5px;width: 10%;">
													<a data-cid="${id}" data-fileName="${icon}"	href="#" onclick="goDoSomething(this);">
													<span class="glyphicon glyphicon-trash"></span></a></td>
												</tr>
											</tbody>
									</table>
								</div>
							</td>
						</tr>
						</c:if>
					</table>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">

					<input type="submit" value="Speichern" class="btn btn-primary" />
					<button class="btn btn-default">
						<a href="/web/category">Abbrechen</a>
					</button>
				</div>
			</div>

		</form:form>

	</div>
</div>


<%@ include file="footer.jsp"%>


<script>
	function goDoSomething(d) {
		var cid = d.getAttribute("data-cid");
		var fileName = d.getAttribute("data-fileName");
		var confirmed = confirm("Möchten Sie dieses ikone wirklich entfernen?");

		if (confirmed) {
			$.ajax({
				url : "/web/deleteCategoryIcon",
				type : 'POST',
				data : {
					"fileName" : fileName,
					"cid" : cid
				},
				success : function(data) {
					console.log(data);

					document.getElementById("iconIndex").remove();
					document.getElementById("iconName").value = "";
					if (data == 'success')
						alert("Ikone erfolgreich entfernt.");
					
				},
			});
		}

	}
</script>