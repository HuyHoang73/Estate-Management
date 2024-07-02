<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customers"/>
<c:url var="formUrl" value="/admin/customer-list"/>
<c:url var="customerEditUrl" value="/admin/customer-edit"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                                <form:form action="/admin/customer-list" modelAttribute="model" method="GET"
                                           id="listForm">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <%--Tên khách hàng--%>
                                            <div class="col-xs-6">
                                                <form:label path="fullname">Tên khách hàng</form:label>
                                                <div class="form-group">
                                                    <form:input path="fullname" class="form-control"/>
                                                </div>
                                            </div>
                                            <%--Email khách hàng--%>
                                            <div class="col-xs-6">
                                                <form:label path="email">Email</form:label>
                                                <div class="form-group">
                                                    <form:input path="email" class="form-control"/>
                                                </div>
                                            </div>
                                            <%--SĐT khách hàng--%>
                                            <div class="col-xs-4">
                                                <form:label path="phone">Số điện thoại</form:label>
                                                <div class="form-group">
                                                    <form:input path="phone" class="form-control"/>
                                                </div>
                                            </div>
                                            <%--Tình trạng--%>
                                            <div class="col-xs-4">
                                                <form:label path="status">Tình trạng</form:label>
                                                <div class="form-group">
                                                    <form:select path="status" class="form-control">
                                                        <form:option value="" label="-----Chọn tình trạng-----"/>
                                                        <form:options items="${statusTransaction}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <%--Nhân viên--%>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <div class="col-xs-4">
                                                    <form:label path="staffId">Chọn nhân viên</form:label>
                                                    <div class="form-group">
                                                        <form:select path="staffId" class="form-control">
                                                            <form:option value="" label="-----Chọn nhân viên-----"/>
                                                            <form:options items="${staffsCustomer}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </security:authorize>
                                        </div>
                                        <!--Nút tìm kiếm-->
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div class="form-group">
                                                    <form:button class="btn btn-info" id="btnSearchCustomer">
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
                    <security:authorize access="hasRole('MANAGER')">
                        <div class="pull-right">
                            <a href="${customerEditUrl}" title="Thêm khách hàng" class="btn btn-white btn-success">
                                <i class="menu-icon fa fas fa-users"></i>
                            </a>
                            <button title="Xóa tòa nhà" class="btn btn-white btn-danger" id="btnDeleteCustomers">
                                <i class="menu-icon fa fas fa-users"></i>
                            </button>
                        </div>
                    </security:authorize>
                </div><!-- /.col -->
            </div>

            <div class="hr hr-18 dotted hr-double"></div>

            <div class="row">
                <div class="col-xs-12">
                    <display:table name="customers.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${formUrl}" partialList="true" sort="external"
                                   size="${customers.totalItems}" defaultsort="3" defaultorder="ascending"
                                   id="tableList" pagesize="${customers.maxPageItems}"
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
                        <display:column headerClass="text-left" property="fullname" title="Họ và tên"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="phone" title="Số điện thoại"/>
                        <display:column headerClass="text-left" property="demand" title="Yêu cầu"/>
                        <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                        <display:column headerClass="text-left" property="status" title="Trạng thái"/>
                        <display:column headerClass="col-actions" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                            onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-align-justify bigger-120"></i>
                                    </button>
                                </security:authorize>

                                <a href="/admin/customer-edit-${tableList.id}" class="btn btn-xs btn-primary"
                                   title="Sửa khách hàng">
                                    <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>
                                </a>

                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="Xóa khách hàng"
                                            onclick="btnDeleteCustomer(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-trash bigger-120"></i>
                                    </button>
                                </security:authorize>
                            </div>
                        </display:column>

                    </display:table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentCustomerModal" role="dialog">
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

                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaffs(customerId);
    }

    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + "/staffs",
            data: JSON.stringify,
            contentType: "application/json; charset-UTF8",
            dataType: "json",
            success: (response) => {
                let row = '';
                $.each(response.data, (index, item) => {
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

    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault();
        let data = {};
        data['customerId'] = $('#customerId').val();
        data['staffs'] = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();

        $.ajax({
            type: "PUT",
            url: "${customerAPI}",
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
    $('#btnDeleteCustomers').click(function (e) {
        e.preventDefault();
        let data = {};
        data['customerIds'] = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteCustomer(data);
    })

    <%--Xóa 1 tòa nhà--%>

    function btnDeleteCustomer(customerId) {
        let data = {};
        data['customerIds'] = customerId;
        deleteCustomer(data);
    }

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/" + data['customerIds'],
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

    $('btnSearchCustomer').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>
