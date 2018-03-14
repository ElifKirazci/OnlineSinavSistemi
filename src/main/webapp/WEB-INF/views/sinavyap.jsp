<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<c:if test="${ not empty sinavdurumu }">
					<div class="row">
						<div class="col-lg-8 col-md-6 col-sm-12"
							style="float: none; margin: 60% auto;">
							<a href='<s:url value="/sinavsorulari"></s:url>'
								class="btn btn-info btn-lg btn-block">Sınava Başla</a>
						</div>
					</div>
				</c:if>
			</div>

			<div class="col-sm-4"></div>
		</div>
	</div>

	<c:if test="${ empty sinavdurumu }">
		<label></label>
		<div class="form-group">
			<div class="p-3 mb-2 bg-info text-white">
				<h3> ${baslik} </h3>
			</div>
		</div>
		<c:if test="${ empty sinavbitti }">
			<div class="row container-fluid">

				<div class="col-sm-12" align="left">
					<div class="card" style="width: 80rem;">
						<div class="card-body">
							<div class="form-group">
								<form action='<s:url value="/sinavsorulari" ></s:url>'
									method="post">
									<h4>${i}-${soru.getSoru()}</h4>
									<label></label>
									<c:if test="${ not empty secenekLs }">
										<c:forEach var="item" items="${secenekLs}">
											<div class="form-group row container-fluid ">
												<div class="custom-control custom-radio">
													<input type="radio" id="${ item.getSecenekid() }"
														name="secilen" value="${ item.getSecenekid() }"
														class="custom-control-input"> <label
														class="custom-control-label"
														for="${ item.getSecenekid() }">${ item.getSecenek() }</label>
												</div>
											</div>
										</c:forEach>
										<div class="row">
											<div class=col-sm-12 align="right">
												<input type="submit" value="SIRADAKI SORU"
													class="btn btn-outline-info">
											</div>
										</div>
									</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>



	<c:if test="${ empty sinavdurumu }">
		<c:if test="${not empty sinavbitti }">
			<div class="row container-fluid">
				<img alt=""
					src="https://www.okulkupu.com/Content/images/anasayfa-sinav.jpg">
				<div class="row">
					<div class="col-lg-2 col-md-10 col-sm-12"
						style="float: none; margin: 70% auto;">
						<a href='<s:url value="/sonuc"></s:url>'
							class="btn btn-lg btn-info btn-lg">Sınav Sonucunu Göster</a>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>

	<div class="col-sm-4"></div>
</body>
</html>