<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Neues Produkt | My Docking</title>
<%@ include file="header.jsp"%>

<div class="container">
	<div class="row content">
		<form:form modelAttribute="product" class="form-horizontal"
			id="product" action="/web/addProduct" method="POST"
			enctype="multipart/form-data">
			<!-- <div class="form-group row">
				<label class="col-sm-2 control-label"> Product Id :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="productId">
				</div>
			</div> -->

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktname :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="itemName" required >
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Beschreibung :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="description">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktlink :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="productLink">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Kategorie wählen :</label>
				<div class="col-sm-4">
					<select name="category" class="form-control ">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}">${category.name} &lt- ${category.parentCategory.name!=null ? category.parentCategory.name : 'N.A.'}  &lt- ${category.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.parentCategory.name : 'N.A.' }</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<!-- <div class="form-group row">
				<label class="col-sm-2 control-label"> Dateiname :</label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="fileNameExtension">
				</div>
			</div> -->

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Produktbild :</label>
				<div class="col-sm-4">
					<input type="file" class="form-control " name="productImages1" multiple="multiple" required />
					<span style="color: tomato;">Empfohlende Pixelgrösse 473px X 484px (iPad), 879px X 329px (iPhone)</span>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label">Produkt PDF :</label>
				<div class="col-sm-4">
					<input type="file" class="form-control " name="productPDFs1"
						multiple="multiple"/>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 control-label"> Bullet Points Überschrift </label>
				<div class="col-sm-4">
					<input type="text" class="form-control " name="keyPointHeading">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 control-label"> Bullet Points :</label>
				<div class="col-sm-4">
					<textarea type="text" class="form-control " name="keyPoints1" placeHolder="Durch ein Komma getrennt"></textarea>
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