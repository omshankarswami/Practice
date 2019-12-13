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
<title>Neues Kategorie | My Docking</title>
<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row content">
		
			<form:form modelAttribute="category" class="form-horizontal"  action="/web/addCategory"
				method="POST" enctype="multipart/form-data">
			
				<!-- <div class="form-group row">
					<label class="col-sm-2 control-label"> Titel :</label>
					<div class="col-sm-4">
						<input type="text" class="form-control " name="title" required="required">
					</div>
				</div> -->
				
				<div class="form-group row">
					<label class="col-sm-2 control-label"> Kategoriename :</label>
					<div class="col-sm-4">
						<input type="text" class="form-control " name="name" required>
					</div>
				</div>
			
				<div class="form-group row">
					<label class="col-sm-2 control-label"> Parent Kategorie :</label>
					<div class="col-sm-4">
						<select name="parentCategory" class="form-control ">
							<option value="0">Kategorie wählen</option>
							<c:forEach items="${categories}" var="category">
								<option	value="${category.id}">${category.name} &lt- ${category.parentCategory.name!=null ? category.parentCategory.name : 'N.A.'}  &lt- ${category.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.name : 'N.A.' }  &lt- ${category.parentCategory.parentCategory.parentCategory.parentCategory.name!=null ? category.parentCategory.parentCategory.parentCategory.parentCategory.name : 'N.A.' }</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- <div class="form-group row">
					<label class="col-sm-2 control-label"> Bearbeiten :</label>
					<div class="col-sm-4">
						<input type="text" class="form-control " name="description">
					</div>
				</div> -->
		
                
				<div class="form-group row">
					<label class="col-sm-2 control-label"> Kategorie Icon :</label>
					<!-- <div class="col-sm-4">
						<input type = "file" class="form-control" id="file" name ="icon1"/> 
						<span style="color: tomato;">Empfohlende Pixelgrösse ZZpx x ZZpx</span>
					</div> -->
					<div class="col-sm-4">
						<table border="1" class="table table-striped table-bordered">
							<tr>
								<td>
									<div class="col-sm-12">
										<input type = "file" class="form-control" id="file" name ="icon1"/>
										<span style="color: tomato;">Empfohlende Pixelgrösse 879px X 329px</span>
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
							<a href="/web/category">Abbrechen</a>
						</button>
					</div>
				</div>

			</form:form>

		</div>
	</div>

<%@ include file="footer.jsp"%>