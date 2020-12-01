<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    th {
        text-align: center;
    }
</style>
<script>

    $(function () {
        $('#videoList').jqGrid({
            styleUI: 'Bootstrap',
            url: "${path}/video/findAll",
            editurl: '${path}/video/edit',
            cellurl: '',
            datatype: "json",
            colNames: ["编号", "标题", "简介", "封面", "视频", "上传时间", "所属级别", "上传人", "所属分组", "状态"],
            colModel: [
                {name: 'id', align: 'center', width: 70,},
                {name: 'title', align: 'center', editable: true, width: 120,},
                {name: 'brief', align: 'center', editable: true, width: 120,},
                {
                    name: 'coverPath', align: 'center', width: 120, height: 120,
                    formatter: function (value, option, row) {
                        return "<img src='" + value + "'width='100px' height='100px'>";
                    }
                },
                {
                    name: 'videoPath', align: 'center', editable: true, width: 210, height: 170, edittype: "file",
                    formatter: function (value, option, row) {
                        return "<video src='" + value + "'width='200px' height='150px' controls='controls'>";
                    }
                },
                {name: 'uploadTime', align: 'center', width: 150,},
                {
                    name: 'categoryId',
                    align: 'center',
                    editable: true,
                    width: 80,
                    edittype: 'select',
                    editoptions: {value: "1:遍历数据库所有二级字段"},
                },
                {name: 'userId', align: 'center', width: 100,},
                {name: 'groupId', align: 'center', width: 88,},
                {
                    name: 'status', align: 'center', width: 88,
                    formatter: function (value, option, row) {
                        if (value == 1) return "<button  onclick='change(\"" + row.id + "\")' id='but' class='btn btn-success'>" + "冻结" + "</button>";
                        if (value == 2) return "<button onclick='change(\"" + row.id + "\")' id='but' class='btn btn-danger'>" + "解冻" + "</button>";
                    }
                }
            ],
            pager: '#pager',
            page: 1,
            rowNum: 2,
            rowList: [4, 8, 10, 20, 30],
            viewrecords: true,
            autowidth: true,
            height: "auto",
            rownumbers: true,//开启表格行号
            multiselect: false,//开启多行选择
        }).jqGrid('navGrid', '#pager', {
                edit: true, add: true, del: true, search: false,
                edittext: '修改',
                addtext: '添加',
                deltext: '删除',
                searchtext: "刷新"
            },
            {
                closeAfterEdit: true,
                reloadAfterSubmit: true,
            },
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterSubmit: function (res) {
                    $.ajaxFileUpload({
                        fileElementId: "videoPath",
                        url: "${pageContext.request.contextPath}/video/upload",
                        type: "post",
                        data: {"uid": res.responseText},
                    });
                }
            },
            {//删除
                closeAfterDel: true,
                reloadAfterSubmit: true,
            }
        );

    });
</script>
<%--面板--%>
<div class="panel panel-warning">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">视频管理</a></li>
    </ul>
    <br>
    <%--数据--%>
    <br>
    <table id="videoList" class="table table-hover table-responsive table-striped"/>
    <%--分页工具栏--%>
    <div id="pager"/>
    <script>
        //   修改状态
        function change(id) {
            $.post("${pageContext.request.contextPath}/video/change", {"id": id}, function (res) {
                $("#userList").jqGrid('clearGridData');
                $("#userList").trigger('reloadGrid');
            }, "text");
        };
    </script>
</div>
