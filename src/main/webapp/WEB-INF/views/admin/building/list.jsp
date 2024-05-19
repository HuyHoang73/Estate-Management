<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/buildings"/>
<c:url var="formUrl" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="page-header">
                <h1>Danh sách tòa nhà</h1>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <span class="widget-toolbar">
										<a href="#" data-action="reload">
											<i class="ace-icon fa fa-refresh"></i>
										</a>

										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>
									</span>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="/admin/building-list" modelAttribute="modelSearch" method="GET" id="listForm">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <form:label path="name">Tên tòa nhà</form:label>
                                                <div class="form-group">
<%--                                                    <input type="text" class="form-control" name="name" id="name" value="${modelSearch.name}"/>--%>
                                                    <form:input path="name" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <form:label path="floorArea">Diện tích sàn</form:label>
                                                <div class="form-group">
                                                    <form:input path="floorArea" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-2">
                                                <form:label path="district">Quận</form:label>
                                                <div class="form-group">
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="" label="--Chọn quận--"/>
                                                        <form:options items="${districtCode}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="col-xs-5">
                                                <form:label path="ward">Phường</form:label>
                                                <div class="form-group">
                                                    <form:input path="ward" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-5">
                                                <form:label path="street">Đường</form:label>
                                                <div class="form-group">
                                                    <form:input path="street" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="numberOfBasement">Số tầng hầm</form:label>
                                                <div class="form-group">
                                                    <form:input path="numberOfBasement" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="direction">Hướng</form:label>
                                                <div class="form-group">
                                                    <form:input path="direction" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="level">Hạng</form:label>
                                                <div class="form-group">
                                                    <form:input path="level" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <form:label path="areaFrom">Diện tích từ</form:label>
                                                <div class="form-group">
                                                    <form:input path="areaFrom" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <form:label path="areaTo">Diện tích đến</form:label>
                                                <div class="form-group">
                                                    <form:input path="areaTo" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <form:label path="rentPriceFrom">Giá thuê từ</form:label>
                                                <div class="form-group">
                                                    <form:input path="rentPriceFrom" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <form:label path="rentPriceTo">Giá thuê đến</form:label>
                                                <div class="form-group">
                                                    <form:input path="rentPriceTo" class="form-control" type="number"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="managerName">Tên quản lý</form:label>
                                                <div class="form-group">
                                                    <form:input path="managerName" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="managerPhone">SĐT quản lý</form:label>
                                                <div class="form-group">
                                                    <form:input path="managerPhone" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <form:label path="staffId">Chọn nhân viên</form:label>
                                                <div class="form-group">
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="" label="--Chọn nhân viên--"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <form:checkboxes path="typeCode" items="${typeCode}"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div class="form-group">
                                                    <form:button class="btn btn-info" id="btnSearchBuilding">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm kiếm
                                                    </form:button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/building-edit" title="Thêm tòa nhà" class="btn btn-white btn-success">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-building-add" viewBox="0 0 16 16">
                                <path
                                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                <path
                                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                <path
                                        d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </a>
                        <button title="Xóa tòa nhà" class="btn btn-white btn-danger" id="btnDeleteBuildings">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-building-dash" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </button>
                    </div>
                </div><!-- /.col -->
            </div>

            <div class="hr hr-18 dotted hr-double"></div>

            <div class="row">
                <div class="col-xs-12">
<%--                    <table id="buildingList" class="table table-striped table-bordered table-hover">--%>
<%--                        <thead>--%>
<%--                            <tr>--%>
<%--                                <th class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace">--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </th>--%>
<%--                                <th>Ngày</th>--%>
<%--                                <th>Tên tòa nhà</th>--%>
<%--                                <th>Địa chỉ</th>--%>
<%--                                <th>Số tầng hầm</th>--%>
<%--                                <th>Tên quản lý</th>--%>
<%--                                <th>Số điện thoại</th>--%>
<%--                                <th>DT sàn</th>--%>
<%--                                <th>DT thuê</th>--%>
<%--                                <th>Giá thuê</th>--%>
<%--                                <th>Phí dịch vụ</th>--%>
<%--                                <th>Phí MG</th>--%>
<%--                                <th>Thao tác</th>--%>
<%--                            </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                            <c:forEach var="item" items="${buildingList}">--%>
<%--                                <tr>--%>
<%--                                    <td class="center">--%>
<%--                                        <label class="pos-rel">--%>
<%--                                            <input type="checkbox" class="ace" value="${item.id}">--%>
<%--                                            <span class="lbl"></span>--%>
<%--                                        </label>--%>
<%--                                    </td>--%>
<%--                                    <td></td>--%>
<%--                                    <td>${item.name}</td>--%>
<%--                                    <td>${item.address}</td>--%>
<%--                                    <td>${item.numberOfBasement}</td>--%>
<%--                                    <td>${item.managerName}</td>--%>
<%--                                    <td>${item.managerPhone}</td>--%>
<%--                                    <td>${item.floorArea}</td>--%>
<%--                                    <td>${item.rentArea}</td>--%>
<%--                                    <td>${item.rentPrice}</td>--%>
<%--                                    <td>${item.serviceFee}</td>--%>
<%--                                    <td>${item.brokerageFee}</td>--%>
<%--                                    <td>--%>
<%--                                        <div class="hidden-sm hidden-xs btn-group">--%>
<%--                                            <button class="btn btn-xs btn-success" title="Giao tòa nhà"--%>
<%--                                                    onclick="assignmentBuilding(${item.id})">--%>
<%--                                                <i class="ace-icon glyphicon glyphicon-align-justify bigger-120"></i>--%>
<%--                                            </button>--%>

<%--                                            <a href="/admin/building-edit-${item.id}" class="btn btn-xs btn-primary" title="Sửa tòa nhà">--%>
<%--                                                <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>--%>
<%--                                            </a>--%>

<%--                                            <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"--%>
<%--                                                    onclick="btnDeleteBuilding(${item.id})">--%>
<%--                                                <i class="ace-icon glyphicon glyphicon-trash bigger-120"></i>--%>
<%--                                            </button>--%>
<%--                                        </div>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
                    <display:table name="buildingList.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${formUrl}" partialList="true" sort="external"
                                   size="${buildingList.totalItems}" defaultsort="3" defaultorder="ascending"
                                   id="tableList" pagesize="${buildingList.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                        headerClass="center select-cell">
                            <fieldset>
                                <label for="checkbox_${tableList.id}"></label>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày"/>
                        <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                        <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                        <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                        <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                        <display:column headerClass="text-left" property="managerPhone" title="SĐT"/>
                        <display:column headerClass="text-left" property="floorArea" title="Diện tích sàn"/>
                        <display:column headerClass="text-left" property="rentArea" title="Diện tích thuê"/>
                        <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                        <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                        <display:column headerClass="text-left" property="brokerageFee" title="Phí MG"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                        onclick="assignmentBuilding(${tableList.id})">
                                    <i class="ace-icon glyphicon glyphicon-align-justify bigger-120"></i>
                                </button>

                                <a href="/admin/building-edit-${tableList.id}" class="btn btn-xs btn-primary" title="Sửa tòa nhà">
                                    <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>
                                </a>

                                <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"
                                        onclick="btnDeleteBuilding(${tableList.id})">
                                    <i class="ace-icon glyphicon glyphicon-trash bigger-120"></i>
                                </button>
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                    </thead>
                    <tbody>
<%--                         <tr>--%>
<%--                             <td class="center">--%>
<%--                                 <label class="pos-rel">--%>
<%--                                     <input type="checkbox" class="ace" id="checkbox_1" value="1" checked="">--%>
<%--                                     <span class="lbl"></span>--%>
<%--                                 </label>--%>
<%--                             </td>--%>
<%--                             <td class="center">Nguyễn Văn A</td>--%>
<%--                         </tr>--%>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);
    }

    function loadStaffs(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
            data: JSON.stringify,
            contentType: "application/json; charset-UTF8",
            dataType: "json",
            success:(response) => {
                let row = '';
                $.each(response.data, (index, item)=>{
                    row += '<tr>';
                    row += '<td class="center"> <label class="pos-rel"><input type="checkbox" class="ace" id="checkbox_' + item.staffId + '" value="' + item.staffId + '" ' + item.checked + '><span class="lbl"></span></label></td>';
                    row += '<td class="center">' + item.fullName + '</td>'
                    row += '</tr>';
                })
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                alert("Thất bại");
                console.log(response);
            }
        })
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        let data = {};
        data['buildingId'] = $('#buildingId').val();
        data['staffs'] = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();

        $.ajax({
            type: "PUT",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json; charset-UTF8",
            // dataType: "json",
            success: function () {
                alert("Giao thành công");
            },
            error: function () {
                alert("Thất bại");
            }
        })
    })

    <%--Xóa nhiều tòa nhà--%>
    $('#btnDeleteBuildings').click(function (e) {
        e.preventDefault();
        let data = {};
        data['buildingIds'] = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteBuilding(data);
    })

    <%--Xóa 1 tòa nhà--%>
    function btnDeleteBuilding(buildingId) {
        let data = {};
        data['buildingIds'] = buildingId;
        deleteBuilding(data);
    }

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data['buildingIds'],
            data: JSON.stringify(data),
            contentType: "application/json; charset-UTF8",
            dataType: "text",
            success: function (response) {
                alert(response);
                window.location.reload();
            },
            error: function (response) {
                alert("Thất bại");
                console.log(response);
            }
        })
    }

    $('btnSearchBuilding').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>
