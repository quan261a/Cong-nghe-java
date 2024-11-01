<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Chỉnh sửa sản phẩm</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2>Chỉnh sửa sản phẩm</h2>

  <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
  </c:if>

  <form method="post" action="editProduct">
    <input type="hidden" name="id" value="${product.id}"/>
    <div class="form-group">
      <label for="name">Tên sản phẩm</label>
      <input type="text" class="form-control" name="name" id="name" value="${product.name}" required>
    </div>
    <div class="form-group">
      <label for="price">Giá sản phẩm</label>
      <input type="number" class="form-control" name="price" id="price" value="${product.price}" required>
    </div>
    <button type="submit" class="btn btn-primary">Cập nhật sản phẩm</button>
  </form>
  <a href="products" class="btn btn-secondary mt-3">Quay lại danh sách sản phẩm</a>
</div>
</body>
</html>
