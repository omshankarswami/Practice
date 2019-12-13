<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<title>Update Produkt | My Docking</title>

<%@ include file="header.jsp"%>


<div class="container">
	<div class="row content">
		<form:form modelAttribute="product" action="/web/updateProduct"
			method="POST" class="form-horizontal" enctype="multipart/form-data">
			<strong>${msg}</strong>
			<input type="hidden" name="id" value="${productId}" />

			<%-- <div class="form-group row">
				<label class="col-sm-2 control-label"> Product Id :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="productId"
						value="${pId}">
				</div>
			</div> --%>

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktname :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="itemName"
						value="${productName}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Beschreibung :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="description"
						value="${description}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktlink :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="productLink"
						value="${productLink}">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label">Kategorie wählen :</label>
				<div class="col-sm-4">
					<select name="category" class="form-control ">
						<c:forEach items="${categories}" var="category">
							<option ${category.id == categoryId ? 'selected="selected"' : ''}
								value="${category.id}">${category.name} &lt- ${category.parentCategory.name!=null ? category.parentCategory.name : 'N.A.'}  &lt- ${category.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.parentCategory.name : 'N.A.' }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<%-- <div class="form-group row">
				<label class="col-sm-2 control-label"> Dateiname :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="fileNameExtension"
						value="${fileNameExtension}">
				</div>
			</div> --%>


			<div class="form-group row">
				<label class="col-sm-2 control-label"> Bullet Points Überschrift </label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="keyPointHeading"
						value="${keyPointHeading}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Bullet Points :</label>
				<div class="col-sm-4">

					<textarea rows="4" cols="50" name="keyPoints" 
					class="form-control mb-3 mr-sm-3"  placeHolder="Durch ein Komma getrennt"><c:forEach var="img3" items="${keyPoints}" varStatus="loop">${img3}${loop.count<keyPoints.size() ? ", " : ""}</c:forEach></textarea>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktbild :</label>
				<div class="col-sm-4">
					<table border="1" class="table table-striped table-bordered">
						<tr>
							<td>
								<div class="col-sm-12">
									<input type="file" class="form-control" name="productImages1" id="upload" multiple="multiple" />
									<span style="color: tomato;">Empfohlende Pixelgrösse 473px X 484px (iPad), 879px X 329px (iPhone)</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="col-sm-12">
									<table border="1" id="table" style="width: inherit;">
										<c:forEach var="productImage" items="${productImages}" varStatus="loop">
											<tbody>
												<tr id="img${loop.index}">
													<td style="padding: 5px"><a href="/api/downloadFile/${productImage}">${productImage}</a></td>
													<td style="padding: 5px;width: 10%;"><a data-pid="${productId}"
														data-fileName="${productImage}" data-index="img${loop.index}"
														href="#" onclick="goDoSomething(this);"> <span
															class="glyphicon glyphicon-trash"></span></a></td>
												</tr>
											</tbody>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label">Produkt PDF :</label>
				<div class="col-sm-4">
					<table border="1" class="table table-striped table-bordered">
						<tr>
							<td>
								<div class="col-sm-12">
									<input type="file" class="form-control" name="productPDFs1"
										multiple="multiple" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="col-sm-12">
									<table border="1" style="width: inherit;">
										<c:forEach var="productPDF" items="${productPDFs}" varStatus="loop1">
											<tbody>
												<tr id="pdf${loop1.index}">
													<td style="padding: 5px"><a href="/api/downloadFile/${productPDF}">${productPDF}</a></td>
													<td style="padding: 5px;width: 10%;"><a data-pid="${productId}"
														data-fileName="${productPDF}" data-index="pdf${loop1.index}"
														href="#" onclick="deletePdf(this);"><span
															class="glyphicon glyphicon-trash"></span></a></td>
												</tr>
											</tbody>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">

					<input type="submit" value="Speichern" class="btn btn-primary" />
					<button class="btn btn-default">
						<a href="/web/products">Abbrechen</a>
					</button>
				</div>
			</div>
		</form:form>

	</div>
</div>



<%@ include file="footer.jsp"%>

<script>
	function goDoSomething(d) {
		var pid = d.getAttribute("data-pid");
		var fileName = d.getAttribute("data-fileName");
		var index = d.getAttribute("data-index");
		var confirmed = confirm("Möchten Sie dieses Bild wirklich löschen?");

		if (confirmed) {
			$.ajax({
				url : "/web/deleteFileAndFileName",
				type : 'POST',
				data : {
					"pname" : fileName,
					"pid" : pid
				},
				success : function(data) {
					console.log(data);

					document.getElementById(index).remove();
					if (data == 'success')
						alert("Bild erfolgreich gelöscht.");
					
				},
			});
		}

	}
</script>
<script>
	function deletePdf(e) {
		var pid = e.getAttribute("data-pid");
		var fileName = e.getAttribute("data-fileName");
		var index1 = e.getAttribute("data-index");
		var confirmed = confirm("Möchten Sie dieses PDF wirklich löschen?");

		if (confirmed) {
			$.ajax({
				url : "/web/deletePdfFileAndFileName",
				type : 'POST',
				data : {
					"pname" : fileName,
					"pid" : pid
				},
				success : function(data) {
					console.log(data);

					document.getElementById(index1).remove();

					if (data == 'success')
						alert("PDF erfolgreich gelöscht.");
					
				},
			});
		}

	}
</script>