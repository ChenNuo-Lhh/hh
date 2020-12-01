<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script src="${pageContext.request.contextPath}/bootstrap/js/echarts.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/china.js"></script>
<script type="text/javascript">

    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));


        $.get("${pageContext.request.contextPath}/user/distribution", function (datas) {

            var series = [];

            for (var i = 0; i < datas.length; i++) {

                var data = datas[i];

                series.push(
                    {
                        name: data.sex,
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.citys
                    }
                )
            }

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '每月用户注册量',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['小男孩', '小姑娘']
                },
                visualMap: {
                    min: 0,
                    max: 2000,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: series
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }, "json");

    });
</script>
<%--设置面板--%>
<div class="panel panel-danger">
    <%--面板头部--%>
    <div class="panel-heading ">
        <h2 class="panel-title"><b>用户分布</b></h2>
    </div>
    <%--面板主体--%>
</div>
