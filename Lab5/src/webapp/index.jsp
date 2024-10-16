<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Quản lý sản phẩm</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger">Username</span> | <a href="logout">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action="addProduct" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>

                <!-- Hiển thị thông báo lỗi nếu có -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        ${errorMessage}
                    </div>
                </c:if>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td><a href="#">Macbook Air M1</a></td>
                    <td>$1,100</td>
                    <td>
                        <a href="#">Chỉnh sửa</a> |
                        <a href="#">Xóa</a>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td><a href="#">Macbook Pro 2020</a></td>
                    <td>$2,400</td>
                    <td>
                        <a href="#">Chỉnh sửa</a> |
                        <a href="#">Xóa</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
