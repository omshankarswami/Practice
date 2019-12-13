<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kategorie | My Docking</title>
<%@ include file="header.jsp"%>

<div class="container">

	<div class="row content">
		<a class="btn btn-info btn-md" href="/web/createCategory"><span>Neue Kategorie hinzufügen</span></a></br> </br>
		<div class="row">
			<div class="table-responsive-md">

				<table id="datatable" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Index</th>
							<th>Name</th>
							<!-- <th>Titel</th> -->
							<!-- <th>Beschreibung</th> -->
							<th>Parent Kategorie</th>
							<th>Icon</th>
							<!-- <th>File Name Extension</th> -->
							<!-- <th>Product Images</th>
								<th>Product PDFs</th>
								<th>Key Points</th> -->
							<th>Bearbeiten</th>
							<th>Löschen</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="category" items="${categoryList}" varStatus="loop">
							<tr>
								<td align="center">${loop.index+1}</td>
								<td>${category.name}</td>
								<%-- <td>${category.title}</td> --%>
								<%-- <td>${category.description}</td> --%>
								<td>${category.parentCategory.name}</td>
								<td><c:if test = "${category.icon != null}"><a href="/api/downloadFile/${category.icon}">${category.icon}</a></c:if></td>
								
								<td align="center"><a class="btn btn-info btn-lg"
									href="/web/editCategory?cid=${category.id}"><span
										class="glyphicon glyphicon-pencil"></span></a></td>
								<td align="center"><a class="btn btn-danger btn-lg deleteProduct" data-id="${category.id}"
									href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>
<script>
$('.deleteProduct').click(function() {
    var id = $(this).data("id");
    var confirmed = confirm("Möchten Sie die Kategorie wirklich löschen?");

    if (confirmed) {
      $.ajax({
          url: "/web/deleteCategory?cid="+id,
          type: 'GET',
          data: 'id='+id,
          success: function(){
              window.location.reload();
          },
      });
    }
});
</script>


<%@ include file="footer.jsp"%>