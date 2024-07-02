<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerEditUrl" value="/admin/customer-edit"/>
<c:url var="customerListUrl" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customers"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="page-header">
                <h1>Thông tin khách hàng</h1>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="customerEdit" action="${customerEditUrl}" method="get" class="form-horizontal" role="form" id="form-edit">
                        <%--Tên khách hàng--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="fullname"> Tên khách hàng </form:label>
                            <div class="col-sm-10">
                                <form:input path="fullname" placeholder="Tên khách hàng" class="form-control"/>
                            </div>
                        </div>
                        <%--Số điện thoại--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="phone">Số điện thoại</form:label>
                            <div class="col-sm-10">
                                <form:input path="phone" placeholder="Số điện thoại" class="form-control"/>
                            </div>
                        </div>
                        <%--Email--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="email">Email</form:label>
                            <div class="col-sm-10">
                                <form:input path="email" placeholder="Email" class="form-control"/>
                            </div>
                        </div>
                        <%--Tên công ty--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="companyName">Tên công ty</form:label>
                            <div class="col-sm-10">
                                <form:input path="companyName" placeholder="Tên công ty" class="form-control"/>
                            </div>
                        </div>
                        <%--Yêu cầu--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="demand">Yêu cầu</form:label>
                            <div class="col-sm-10">
                                <form:input path="demand" placeholder="Yêu cầu" class="form-control"/>
                            </div>
                        </div>
                        <%--Tình trạng--%>
                        <div class="form-group">
                            <form:label class="col-sm-2 control-label no-padding-right" path="status">Tên quận</form:label>
                            <div class="col-sm-10">
                                <form:select id="status" path="status" class="form-control">
                                    <form:option value="">------Chọn tình trạng-----</form:option>
                                    <form:options items="${statusTransaction}"/>
                                </form:select>
                            </div>
                        </div>
                        <%--Thao tác--%>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-5 col-md-7">
                                <security:authorize access="hasRole('MANAGER')">
                                    <c:if test="${empty customerEdit.id}">
                                        <button class="btn btn-primary" type="button" onclick="btnAddOrUpdateCustomer(${customerEdit.id})">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            Thêm khách hàng
                                        </button>
                                    </c:if>
                                    <c:if test="${not empty customerEdit.id}">
                                        <button class="btn btn-primary" type="button" onclick="btnAddOrUpdateCustomer(${customerEdit.id})">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            Sửa khách hàng
                                        </button>
                                    </c:if>
                                </security:authorize>
                                &nbsp; &nbsp; &nbsp;
                                <a href="${customerListUrl}">
                                    <button type="button" class="btn btn-danger">
                                        <i class="ace-icon fa fa-undo bigger-110"></i>
                                        Quay lại
                                    </button>
                                </a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>

            <c:forEach var="item" items="${typeTransaction}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}','${customerEdit.id}')">
                            <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                        </button>
                    </div>
                    <c:if test="${item.key == 'CSKH'}">
                        <display:table name="cskhTransactions.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${customerEditUrl}" partialList="true" sort="external"
                                       size="${cskhTransactions.totalItems}" defaultsort="3" defaultorder="ascending"
                                       id="tableList" pagesize="${cskhTransactions.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column headerClass="text-left" property="createdDate" title="Ngày tạo"/>
                            <display:column headerClass="text-left" property="createdBy" title="Nguời tạo"/>
                            <display:column headerClass="text-left" property="modifiedDate" title="Ngày sửa"/>
                            <display:column headerClass="text-left" property="modifiedBy" title="Người sửa"/>
                            <display:column headerClass="text-left" property="note" title="Chi tiết giao dịch"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-primary" onclick="updateTransaction('${tableList.id}','${tableList.note}','${item.key}','${customerEdit.id}')">
                                        <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>
                                    </button>
                                </div>
                            </display:column>
                        </display:table>
                    </c:if>

                    <c:if test="${item.key == 'DDX'}">
                        <display:table name="ddxTransactions.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${customerEditUrl}" partialList="true" sort="external"
                                       size="${ddxTransactions.totalItems}" defaultsort="3" defaultorder="ascending"
                                       id="tableList" pagesize="${ddxTransactions.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column headerClass="text-left" property="createdDate" title="Ngày tạo"/>
                            <display:column headerClass="text-left" property="createdBy" title="Nguời tạo"/>
                            <display:column headerClass="text-left" property="modifiedDate" title="Ngày sửa"/>
                            <display:column headerClass="text-left" property="modifiedBy" title="Người sửa"/>
                            <display:column headerClass="text-left" property="note" title="Chi tiết giao dịch"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
<%--                                    <a href="/admin/customer-edit-${tableList.id}" class="btn btn-xs btn-primary"--%>
<%--                                       title="Sửa giao dịch">--%>
<%--                                        <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>--%>
<%--                                    </a>--%>
                                    <button class="btn btn-xs btn-primary" onclick="updateTransaction('${tableList.id}','${tableList.note}','${item.key}','${customerEdit.id}')">
                                        <i class="ace-icon glyphicon glyphicon-edit bigger-120"></i>
                                    </button>
                                </div>
                            </display:column>
                        </display:table>
                    </c:if>

                </div>
            </c:forEach>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="note" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="note" class="width-100" />
                        </span>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value=""/>
                <input type="hidden" name="code" id="code" value=""/>
                <input type="hidden" name="id" id="id" value=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function btnAddOrUpdateCustomer(customerId) {
        let data = {};
        let formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            data["" + it.name + ""] = it.value;
        });
        if(customerId != null){
            data['id'] = customerId;
        }
        console.log(data);
        if("" === data['fullname']) {
            alert("Bắt buộc phải nhập tên");
        } else if("" === data['phone']){
            alert("Bắt buộc phải nhập số điện thoại");
        } else if("" === data['status']){
            alert("Bắt buộc phải có tình trang xử lý");
        } else {
            btnAddOrUpdate(data);
        }
    }

    function btnAddOrUpdate(data) {
        $.ajax({
            type: "POST",
            url: "/api/customers",
            data: JSON.stringify(data),
            contentType: "application/json; charset-UTF8",
            dataType: "text",
            success: (response) => {
                alert(response);
                window.location.replace("/admin/customer-list")
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            }
        })
    }

    function transactionType(code, customerId){
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
        $('#note').val("");
        $('#id').val(null);
    }

    function updateTransaction(id, note, code, customerId){
        $('#transactionTypeModal').modal();
        $('#id').val(id);
        $('#note').val(note);
        $('#customerId').val(customerId);
        $('#code').val(code);
    }

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        let data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['note'] = $('#note').val();
        addTransaction(data);
    })

    function addTransaction(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}/" + "transaction",
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
</script>
</body>
</html>
