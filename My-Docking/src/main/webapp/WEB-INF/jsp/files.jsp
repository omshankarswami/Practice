<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Videos | My Docking</title>




<%@ include file="header.jsp"%>


<div class="container">
	<div class="row content">

		<%-- <%@ include file = "header.jsp" %>  --%>

		<div class="row">
			<div class="table-responsive-md">

				<table id="datatable" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Index</th>
							<th>Videotitel</th>
							<th>Videopfad</th>
							<th>Thumbnail Pfad</th>
							<!-- <th>Status</th> -->
							<th>Kategorie</th>
							<th>Status</th>
							<th>Löschen</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="supportfile" items="${fileList}"  varStatus="loop">
							<tr>
								<td align="center">${loop.index+1}</td>
								<td>${supportfile.name}</td>
								<td><a href="${supportfile.path}">${supportfile.path}</a></td>
								<td><a href="${supportfile.thumbnail}">${supportfile.thumbnail}</a></td>
								<%-- <td>${supportfile.approved == true ? 'Approved' : supportfile.approved == false ? 'Not Approved' : 'Pending' }</td> --%>
								<td>${supportfile.category.name}</td>
								<td align="center">								
									<c:choose>
									    <c:when test="${supportfile.approved == true}">
									        <a class="btn btn-warning"	href="/web/updateStatus/${supportfile.id}/false">Rückgängig</a>
									    </c:when>    
									    <c:when test="${supportfile.approved == false}">
									        <a class="btn btn-success"	href="/web/updateStatus/${supportfile.id}/true">Bestätigen</a>
									    </c:when>
									    <c:otherwise>
									       <a class="btn btn-success"	href="/web/files">No Action</a>
									    </c:otherwise>
									</c:choose>
								</td>
								<td align="center"><a class="btn btn-danger btn-lg deleteFile" data-id="${supportfile.id}"
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
$('.deleteFile').click(function() {
    var id = $(this).data("id");
    var confirmed = confirm("Möchten Sie die Datei wirklich löschen?");

    if (confirmed) {
      $.ajax({
          url: "/web/deleteFile?id="+id,
          type: 'GET',
          success: function(){
              window.location.reload();
          },
      });
    }
});
</script>

<%@ include file="footer.jsp"%>