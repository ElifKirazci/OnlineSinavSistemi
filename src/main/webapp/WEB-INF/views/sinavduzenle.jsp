<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<div class="container-fluid">

<div class="p-3 mb-2 bg-info text-white"><h3>Soru Kontrolü </h3></div>
 <br>
  <div class="row">
    <div class="col-sm-4">
   <div class="card" style="width: 25rem;">
  <div class="card-body">
    <h5 class="card-title">Soru</h5>
    <c:if test="${not empty hata}">
  <div class="alert alert-danger alert-dismissible fade show" role="alert">
  <strong>Hata!</strong> ${hata}
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
</c:if>
    <form action='<s:url value="/soruekleme "></s:url>' method="post">
    <textarea class="form-control" name="soru"  rows="3"></textarea><br>
    <div align="right">
    <input type="submit" class="btn btn-outline-info btn-lg" value="Soru Ekle"></div>
</form>
</div>
</div>
</div>

    <div class="col-sm-8">
    <div class="card" style="width: 50rem;">
<table class="table table-hover">
  <thead>
    <tr style="background-color:#CFECEC">
    
      <th scope="col">No</th>
      <th scope="col">Soru</th>
      <th scope="col">İşlem</th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="item" items="${sol}">
    <tr>
      <th scope="row"> ${item.getSorunumarasi()} </th>
      <td> ${item.getSoru()} </td>
      <td ><a href='<s:url value="/secenekekle/${item.getSoruid()}"></s:url>' class="btn btn-outline-info col-sm-12">Seçenek Ekle</a> 
     <a href='<s:url value="/sorusil/${item.getSoruid()} "></s:url>' class="btn btn-outline-danger col-sm-12">Soru Sil</a>
      </td>
    </tr> 
  </c:forEach>
  </tbody>
</table>
  </div> 
</div>
    </div>
    </div>
</body>
</html>