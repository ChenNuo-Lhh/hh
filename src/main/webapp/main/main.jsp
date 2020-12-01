<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${path}/login/assets/js/jquery.validate.min.js"></script>
    <script src="${path}/bootstrap/js/echarts.js"></script>
    <script src="${path}/bootstrap/js/china.js"></script>


</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.jsp" style="margin-left: 10px">
                <!--log图片-->
                <img alt="Brand" height="20" width="20"
                     src="https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/11.png">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!--左部-->
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/main/main.jsp">应学视频APP后台管理系统</a></li>
            </ul>
            <!--右部-->
            <ul class="nav navbar-nav navbar-right">
                <%--欢迎--%>
                <span class="navbar-brand"><span style="color: #c0c0c0">欢迎:</span><span
                        class="text-primary">${sessionScope.admin.username}</span></span>
                <%--退出--%>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/del">退出登陆</a>
                <span class="glyphicon glyphicon-log-in navbar-brand" aria-hidden="true"></span>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--栅格系统-->
<div class="container-fluid">
    <div class="row">
        <!--左边手风琴部分-->
        <div class="col-md-2" style="margin-left: 10px">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <!--用户管理-->
                <div class="panel panel-danger" style="padding: 0px">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne"
                         style="padding: 0px">
                        <div class="panel-body" style="padding: 0px">
                            <ul class="list-group" style="margin: 0px;padding: 0px;text-align: center;">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/user/showUser.jsp');">用户展示</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/user/Statistics.jsp');">用户统计</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/user/distribution.jsp');">用户分布</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--分类管理-->
                <div class="panel panel-success">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body" style="padding: 0px">
                            <ul class="list-group" style="margin: 0px;padding: 0px;text-align: center;">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/category/showCategory.jsp');">分类展示</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">占位</button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">占位</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--视频管理-->
                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                视频管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body" style="padding: 0px">
                            <ul class="list-group" style="margin: 0px;padding: 0px;text-align: center;">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-warning">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/video/videoList.jsp');">视频展示</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-warning">占位</button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-warning">占位</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--反馈管理-->
                <div class="panel panel-danger">
                    <div class="panel-heading" role="tab" id="headingFor">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFor" aria-expanded="false" aria-controls="collapseFor">
                                反馈管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFor" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFor">
                        <div class="panel-body" style="padding: 0px">
                            <ul class="list-group" style="margin: 0px;padding: 0px;text-align: center;">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/feedback/feedback.jsp');">反馈管理</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">占位</button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-success">占位</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--日志管理-->
                <div class="panel panel-info">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFive">
                        <div class="panel-body" style="padding: 0px">
                            <ul class="list-group" style="margin: 0px;padding: 0px;text-align: center;">
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:;"
                                           onclick="javascript:$('#content').load('${path}/Log/LogList.jsp');">日志管理</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">占位</button>
                                </li>
                                <li class="list-group-item">
                                    <button type="button" class="btn btn-info">占位</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--右边主体-->
        <div class="col-md-10 navbar-right">
            <div id="content" style="margin-right: 20px">
                <div>
                    <!--巨幕开始-->
                    <div class="jumbotron" style="height: 150px;padding-top: 20px">
                        <h1>欢迎来到应学APP后台管理系统</h1>
                    </div>
                    <!--右边轮播图部分-->
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        </ol>

                        <!-- 轮播图 -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic1.jpg"
                                     style="width: 100%;height: 400px;" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic2.jpg"
                                     style="width: 100%;height: 400px;" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic3.jpg"
                                     style="width: 100%;height: 400px;" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                            <div class="item">
                                <img src="${pageContext.request.contextPath}/bootstrap/img/pic4.jpg"
                                     style="width: 100%;height: 400px;" alt="...">
                                <div class="carousel-caption">
                                    ...
                                </div>
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button"
                           data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--页脚-->
<nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top: 15px">
    <div class="container" style="text-align: center">
        <p>百知教育&copy;Liu Donghao&reg;科技有限公司</p>
    </div>
</nav>
</body>
</html>
