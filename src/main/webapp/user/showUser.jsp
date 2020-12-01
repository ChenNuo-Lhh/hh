<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            styleUI: "Bootstrap",
            url: "${pageContext.request.contextPath}/user/userList",
            datatype: "json",
            colNames: ["ID", "头像", "名字", "状态", "简介", "手机号", "注册时间"],
            colModel: [
                {name: "id"},
                {
                    name: "picImg", editable: true, edittype: "file",
                    formatter: function (value, option, row) {
                        return "<img src='" + value + "'width='50px' href='50px'>";
                    }
                },
                {name: "nickName", editable: true,},
                {
                    name: "status",
                    formatter: function (value, option, row) {
                        if (value == 1) return "<button  onclick='change(\"" + row.id + "\")' id='but' class='btn btn-success'>" + "冻结" + "</button>";
                        if (value == 2) return "<button onclick='change(\"" + row.id + "\")' id='but' class='btn btn-danger'>" + "解冻" + "</button>";
                    }
                },
                {name: "brief", editable: true,},
                {name: "phone", editable: true,},
                {name: "createDate",}
            ],
            autowidth: true,
            pager: "#pager",
            page: 1,
            rowNum: 4,
            rowList: [5, 6, 7, 8, 9, 10],
            viewrecords: true,
            editurl: "${pageContext.request.contextPath}/user/edit",
        }).navGrid("#pager", {add: true, edit: true, del: false, refresh: true, search: false},
            {
                //修改
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,

            },
            {
                //添加
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterSubmit: function (res) {
                    console.log(res.responseText);
                    $.ajaxFileUpload({
                        fileElementId: "picImg",
                        url: "${pageContext.request.contextPath}/user/upload",
                        type: "post",
                        data: {"uid": res.responseText},
                        success: function () {
                        }
                    });
                }
            }
        );
        $("#aliyun").click(function () {
            var val1 = $("#phone").val();
            console.log(val1);
            $.post("${pageContext.request.contextPath}/user/sendOut", {"phone": val1}, function (res) {
                console.log(res);
                alert(res);
            }, "text");
        });
    });
</script>
<%--设置面板--%>
<div class="panel panel-danger">
    <%--面板头部--%>
    <div class="panel-heading ">
        <h2 class="panel-title"><b>用户展示</b></h2>
    </div>
    <%--面板主体--%>
    <div class="panel-body" id="userTable">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">用户管理</a></li>
                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab"
                                           data-toggle="tab">Profile</a></li>
                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a>
                </li>
                <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content" style="margin-top: 20px;padding-left: 10px">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <%--左侧--%>
                    <div class="pull-left col-md-5">
                        <button id="DC" type="button" class="btn btn-success">导出用户信息</button>
                        <button id="DR" type="button" class="btn btn-info">导入用户</button>
                        <button type="button" class="btn btn-warning">测试按钮</button>
                    </div>
                    <script>
                        $("#DC").click(function () {
                            $.getJSON("${pageContext.request.contextPath}/user/exportUser", function (res) {

                            });
                        });
                        $("#DR").click(function () {
                            $.getJSON("${pageContext.request.contextPath}/user/importUser", function (res) {

                            });
                        });
                    </script>
                    <%--右侧--%>
                    <div class="pull-right col-md-5" style="padding: 0px;">
                        <form>
                            <div class="col-md-8 col-md-offset-1" style="padding: 0px;">
                                <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号..."
                                       required
                                       minlength="11">
                            </div>
                            <div class="col-md-3 pull-right" style="padding: 0px;">
                                <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                            </div>
                        </form>
                    </div>
                    &nbsp;&nbsp;
                    <hr>
                    <div>
                        <%--员工列表--%>
                        <table id="userList"></table>
                        <%--指定分页工具栏--%>
                        <div id="pager"></div>
                        <script>
                            //   修改状态
                            function change(id) {
                                $.post("${pageContext.request.contextPath}/user/change", {"id": id}, function (res) {
                                    $("#userList").jqGrid('clearGridData');
                                    $("#userList").trigger('reloadGrid');
                                }, "text");
                            };
                        </script>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="profile">...</div>
                <div role="tabpanel" class="tab-pane" id="messages">...</div>
                <div role="tabpanel" class="tab-pane" id="settings">...</div>
            </div>
        </div>
    </div>
</div>
