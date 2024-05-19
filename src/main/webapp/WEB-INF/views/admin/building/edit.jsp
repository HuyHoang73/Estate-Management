<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingEditUrl" value="/admin/building-edit"/>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<c:url var="buildingImage" value="/admin/image/default.png"/>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="page-header">
                <h1>Thông tin tòa nhà</h1>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="buildingEdit" action="${buildingEditUrl}" method="get" class="form-horizontal" role="form" id="form-edit">
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="name"> Tên tòa nhà </form:label>
                            <div class="col-sm-10">
                                <form:input path="name" placeholder="Tên tòa nhà" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="district">Tên quận</form:label>
                            <div class="col-sm-10">
                                <form:select id="district" path="district" class="form-control">
                                    <form:option value="">------Chọn quận-----</form:option>
                                    <form:options items="${districtCode}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="ward">Phường</form:label>
                            <div class="col-sm-10">
                                <form:input path="ward" placeholder="Tên phường" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="street">Đường</form:label>
                            <div class="col-sm-10">
                                <form:input path="street" placeholder="Tên đường" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="structure">Kết cấu</form:label>
                            <div class="col-sm-10">
                                <form:input path="structure" placeholder="Kết cấu" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="numberOfBasement">Số tầng hầm</form:label>
                            <div class="col-sm-10">
                                <form:input path="numberOfBasement" placeholder="Số tầng hầm" class="form-control" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="floorArea">Diện tích sàn</form:label>
                            <div class="col-sm-10">
                                <form:input path="floorArea" placeholder="Diện tic sàn" class="form-control" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="direction">Hướng</form:label>
                            <div class="col-sm-10">
                                <form:input path="direction" placeholder="Hướng" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="level">Hạng</form:label>
                            <div class="col-sm-10">
                                <form:input path="level" placeholder="Hạng" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="rentArea">Diện tích thuê</form:label>
                            <div class="col-sm-10">
                                <form:input path="rentArea" placeholder="Diện tích thuê" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="rentPrice">Giá thuê</form:label>
                            <div class="col-sm-10">
                                <form:input path="rentPrice" placeholder="Giá thuê" class="form-control" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="rentPriceDescription">Mô tả giá</form:label>
                            <div class="col-sm-10">
                                <form:input path="rentPriceDescription" placeholder="Mô tả giá" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="serviceFee">Phí dịch vụ</form:label>
                            <div class="col-sm-10">
                                <form:input path="serviceFee" placeholder="Phí dịch vụ" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="carFee">Phí ô tô</form:label>
                            <div class="col-sm-10">
                                <form:input path="carFee" placeholder="Phí ô tô" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="motoFee">Phí xe máy</form:label>
                            <div class="col-sm-10">
                                <form:input path="motoFee" placeholder="Phí xe máy" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="overtimeFee">Phí ngoài giờ</form:label>
                            <div class="col-sm-10">
                                <form:input path="overtimeFee" placeholder="Phí ngoài giờ" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="waterFee">Tiền nước</form:label>
                            <div class="col-sm-10">
                                <form:input path="waterFee" placeholder="Tiền nước" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="electricityFee">Tiền điện</form:label>
                            <div class="col-sm-10">
                                <form:input path="electricityFee" placeholder="Tiền điện" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="deposit">Đặt cọc</form:label>
                            <div class="col-sm-10">
                                <form:input path="deposit" placeholder="Tiền đặt cọc" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="payment">Thanh toán</form:label>
                            <div class="col-sm-10">
                                <form:input path="payment" placeholder="Thanh toán" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="rentTime">Thời hạn thuê</form:label>
                            <div class="col-sm-10">
                                <form:input path="rentTime" placeholder="Thời hạn thuê" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="decorationTime">Thời gian trang trí</form:label>
                            <div class="col-sm-10">
                                <form:input path="decorationTime" placeholder="Thời gian trang trí" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="managerName">Tên quản lý</form:label>
                            <div class="col-sm-10">
                                <form:input path="managerName" placeholder="Tên quản lý" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="managerPhone">SĐT quản lý</form:label>
                            <div class="col-sm-10">
                                <form:input path="managerPhone" placeholder="Số điện thoại quản lý" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="brokerageFee">Phí môi giới</form:label>
                            <div class="col-sm-10">
                                <form:input path="brokerageFee" placeholder="Phí môi giới" class="form-control" type="number"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="typeCode">Loại tòa nhà</form:label>
                            <div class="col-sm-10">
                                <form:checkboxes path="typeCode" items="${typeCode}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="note">Ghi chú</form:label>
                            <div class="col-sm-10">
                                <form:textarea path="note" placeholder="Ghi chú" rows="7" class="form-control"/>
                            </div>
                        </div>
                        <%--Hình ảnh--%>
                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <%--Có ảnh--%>
                                <c:if test="${not empty buildingEdit.image}">
                                    <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px" alt=""/>
                                </c:if>

                                <%--Không có ảnh--%>
                                <c:if test="${empty buildingEdit.image}">
                                    <img src="${buildingImage}" id="viewImage" width="300px" height="300px" alt=""/>
                                </c:if>
                            </div>
                        </div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-5 col-md-7">
                                <c:if test="${empty buildingEdit.id}">
                                    <button class="btn btn-primary" type="button" onclick="btnAddOrUpdateBuilding(${buildingEdit.id})">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Thêm tòa nhà
                                    </button>
                                </c:if>
                                <c:if test="${not empty buildingEdit.id}">
                                    <button class="btn btn-primary" type="button" onclick="btnAddOrUpdateBuilding(${buildingEdit.id})">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Sửa tòa nhà
                                    </button>
                                </c:if>
                                &nbsp; &nbsp; &nbsp;
                                <a href="${buildingListUrl}">
                                    <button type="button" class="btn btn-danger">
                                        <i class="ace-icon fa fa-undo bigger-110"></i>
                                        Hủy thao tác
                                    </button>
                                </a>
                            </div>
                        </div>

                    </form:form>
                </div><!-- /.col -->
            </div>

            <div class="row">
                <div class="col-xs-12">

                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script>
    var imageBase64 = '';
    var imageName = '';

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#uploadImage').change(function (event) {
        let reader = new FileReader();
        let file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name;
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function btnAddOrUpdateBuilding(buildingId) {
        var data = {};
        var typeCode = [];
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            if (it.name != 'typeCode' && '' !== it.value && null != it.value) {
                data["" + it.name + ""] = it.value;
            } else {
                typeCode.push(it.value);
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data['typeCode'] = typeCode;
        if(buildingId != null){
            data['id'] = buildingId;
        }
        console.log(data);
        if(typeCode.length === 0) {
            alert("Chưa có loại tòa nhà");
        } else {
            btnAddOrUpdate(data);
        }
    }

    function btnAddOrUpdate(data) {
        $.ajax({
            type: "POST",
            url: "/api/buildings",
            data: JSON.stringify(data),
            contentType: "application/json; charset-UTF8",
            dataType: "text",
            success: (response) => {
                alert(response);
                window.location.replace("/admin/building-list")
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            }
        })
    }
</script>
</body>
</html>
