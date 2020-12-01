<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            styleUI: "Bootstrap",
            url: "${pageContext.request.contextPath}/Log/findAll",
            datatype: "json",
            colNames: ["ID", "用户", "时间", "事件", "结果"],
            colModel: [
                {name: "id"},
                {name: "name"},
                {name: "time"},
                {name: "options"},
                {
                    name: "status",
                    formatter: function (value, option, row) {
                        if (value == 'success') return "<button  id='but' class='btn btn-success'>" + "成功" + "</button>";
                        if (value == 'error') return "<button  id='but' class='btn btn-danger'>" + "失败" + "</button>";
                    }
                }
            ],
            autowidth: true,
            pager: "#pager",
            page: 1,
            rowNum: 4,
            rowList: [5, 6, 7, 8, 9, 10],
            viewrecords: true,
        }).navGrid("#pager", {add: false, edit: false, del: false, refresh: true, search: false});
    });
</script>
<%--设置面板--%>
<div class="panel panel-info">
    <%--面板头部--%>
    <div class="panel-heading ">
        <h2 class="panel-title"><b>日志信息</b></h2>
    </div>
    <%--面板主体--%>
    <div class="panel-body" id="userTable">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志管理</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content" style="margin-top: 20px;padding-left: 10px">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div>
                        <%--员工列表--%>
                        <table id="userList"></table>
                        <%--指定分页工具栏--%>
                        <div id="pager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
