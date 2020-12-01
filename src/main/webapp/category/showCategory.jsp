<%@page pageEncoding="UTF-8" isELIgnored="false" %>


<script>

    $(function () {
        pageInit();
    });

    /**
     * 父表
     * */
    function pageInit() {
        $("#cateTable").jqGrid({
            url: "${pageContext.request.contextPath}/category/queryOneAll",
            datatype: "json",
            rowNum: 4,
            rowList: [8, 10, 20, 30],
            page: 1,
            pager: '#catePage',
            sortname: 'id',
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            colNames: ['ID', '名称', '级别'],
            colModel: [
                {name: 'id', index: 'id', width: 55},
                {name: 'cateName', index: 'invdate', width: 90, editable: true},
                {
                    name: 'levels',
                    index: 'name',
                    width: 100,
                    editable: true,
                    edittype: 'select',
                    editoptions: {value: "1:1"}
                }
            ],
            editurl: "${pageContext.request.contextPath}/category/edit",
            subGrid: true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        /**
         * add: false,  添加
         * edit: true,  修改
         * del: false,  删除
         * refresh: true, 刷新
         * search: false    查询
         **/
        $("#cateTable").jqGrid('navGrid', '#catePage', {add: true, edit: true, del: true, refresh: true, search: false},
            {
                //修改
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            },
            {
                //添加
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            },
            {
                //删除
                closeAfterDel: true,
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            }
        );
    }


    /**
     * 子表
     * */
    function addSubGrid(subgridId, rowId) {

        var subgridTableTd = subgridId + "Table";
        var pagerId = subgridId + "Page";


        $("#" + subgridId).html("" +
            "<table id='" + subgridTableTd + "' />" +
            "<div id='" + pagerId + "' />"
        );


        $("#" + subgridTableTd).jqGrid({
            url: "${pageContext.request.contextPath}/category/queryTwoAll?id=" + rowId,
            datatype: "json",
            rowNum: 4,
            rowList: [8, 10, 20, 30],
            page: 1,
            pager: "#" + pagerId,
            sortname: 'num',
            sortorder: "asc",
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            colNames: ['ID', '名称', '级别', '父类ID'],
            colModel: [
                {name: "id", index: "num", width: 80, key: true,},
                {name: "cateName", index: "item", width: 130, editable: true,},
                {
                    name: "levels",
                    index: "qty",
                    width: 70,
                    align: "right",
                    editable: true,
                    edittype: 'select',
                    editoptions: {value: "2:2"}
                },
                {name: "parentId", index: "unit", width: 70, align: "right"}
            ],
            editurl: "${pageContext.request.contextPath}/category/edit?parentId=" + rowId,

        });

        $("#" + subgridTableTd).jqGrid('navGrid', "#" + pagerId, {
                add: true,
                edit: true,
                del: true,
                refresh: true,
                search: false
            },
            {
                //修改
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            },
            {
                //添加
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            },
            {
                //删除
                closeAfterDel: true,
                reloadAfterSubmit: true,
                afterComplete: function (res) {
                    if (res.responseText != '') alert(res.responseText);
                }
            }
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a>
        </li>
    </ul>

    <%--表单--%>
    <table id="cateTable"/>

    <%--分页工具栏--%>
    <div id="catePage"/>

</div>

<%--
    删除要有提示信息
--%>
