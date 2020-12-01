<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.getJSON("${pageContext.request.contextPath}/user/Statistics", function (row) {

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'Step Line'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['女', '男']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    data: row.month,
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: 'girl',
                        type: 'line',
                        step: 'start',
                        data: row.girl,
                    },
                    {
                        name: 'boy',
                        type: 'line',
                        step: 'middle',
                        data: row.boy
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });
    });
</script>
<%--设置面板--%>
<div class="panel panel-danger">
    <%--面板头部--%>
    <div class="panel-heading ">
        <h2 class="panel-title"><b>用户统计</b></h2>
    </div>
    <%--面板主体--%>
    <div>
        <div id="main" style="width: 600px;height:400px;">

        </div>
    </div>
</div>
