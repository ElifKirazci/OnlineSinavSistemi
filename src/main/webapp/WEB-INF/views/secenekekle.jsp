<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<br>
	<div class="container-fluid">
		<div class="p-3 mb-2 bg-info text-white"><h3>Seçenek Ekleme</h3></div>
		<br>
		<div class="card" style="background-color:#CFECEC" style="width: 80rem;">
			<div class="card-body">
				<c:if test="${ not empty hata }">
	${ hata }
</c:if>
		<h5><label class="col-sm-12" for="adi">${sell}</label></h5><br>
				<form action='<s:url value="/secekle/${sid}"></s:url>' method="POST">
					<div class="form-inline">
					<label class="col-sm-2" for="adi">Seçenek Ekle</label> 
						<input name="secenek" type="text" class="form-control col-sm-5"
							placeholder="Adınız"> <select name=durum
							class="btn btn-outline-secondary">
							<option value="YANLIŞ">YANLIŞ</option>
							<option value="DOĞRU">DOĞRU</option>
						</select> <input type="submit" class="btn btn-outline-info"
							value="Seçenek Ekle">
					</div>
				</form>
				<br>
				<c:forEach items="${seclist}" var="items">
					<div class="form-inline">
					
						<label class="col-sm-2" for="adi"></label> 
						<label class="form-control col-sm-4" for="adi">${items.getSecenek()}</label>
						<label class="form-control col-sm-2">${items.getDurum()}</label>
						<a href='<s:url value="/seceneksil/${items.getSoruid()}/${items.getSecenekid()} "></s:url>'
							class="btn btn-outline-danger">Secenek Sil</a>
					</div>
					<label></label>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>