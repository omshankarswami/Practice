<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Produkte | My Docking</title>

<%@ include file="header.jsp"%>

<div class="container">
	<div class="row content">
		<a class="btn btn-info btn-md" href="/web/createProduct"><span>Neues Produkt hinzufügen</span></a></br> </br>
		<div class="row">
			<div class="table-responsive-md">

				<table id="datatable" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Index</th>
							<!-- <th>Product Id</th> -->
							<th>Produktname</th>
							<th>Beschreibung</th>
							<th>Kategorie</th>
							<!-- <th>Bulletpoints Überschrift</th> -->
							<!-- <th>File Name Extension</th> -->
							<!-- <th>images name</th> -->
							<!-- <th>Product Images</th>
								<th>Product PDFs</th>
								<th>Key Points</th> -->
							<th>Bearbeiten</th>
							<th>Löschen</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="product" items="${proudctList}" varStatus="loop">
							<tr>
								<td align="center">${loop.index+1}</td>
								<%-- <td>${product.productId}</td> --%>
								<td>${product.itemName}</td>
								<td>${product.description}</td>
								<td>${product.category.name}</td>
								<%-- <td>${product.keyPointHeading}</td> --%>
								<%-- <td>${product.fileNameExtension}</td> --%>
								<%-- <td><c:forEach var="img1" items="${product.productImages}">${img1}</c:forEach></td> --%>
								<%-- <td><c:forEach var="img1" items="${product.productImages}">${img1}</c:forEach></td>
									<td><c:forEach var="img2" items="${product.productPDFs}">${img2}</c:forEach></td>
									<td><c:forEach var="img3" items="${product.keyPoints}">${img3}</c:forEach></td> --%>
								<td><a class="btn btn-info btn-lg"
									href="/web/update?pid=${product.id}"><span
										class="glyphicon glyphicon-pencil"></span></a></td>
								<td><a class="btn btn-danger btn-lg deleteProduct" data-id="${product.id}"
									><span class="glyphicon glyphicon-trash"></span></a></td>
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
    var confirmed = confirm("Möchten Sie wirklich das Produkt löschen?");

    if (confirmed) {
      $.ajax({
          url: "/web/delete?pid="+id,
          type: 'GET',
          success: function(){
              window.location.reload();
          },
      });
    }
});
</script>

<%@ include file="footer.jsp"%>