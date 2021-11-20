<%--
  Created by IntelliJ IDEA.
  User: xw
  Date: 2019/12/24
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <jsp:include page="/WEB-INF/jsp/common/userinfo.jsp"></jsp:include>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form id="updateForm">
                        <div class="form-group">
                            <label for="floginacct">登陆账号</label>
                            <input type="text" class="form-control" name="floginacct" id="floginacct" value="${user.loginacct}">
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group">
                            <label for="fusername">用户名称</label>
                            <input type="text" class="form-control" id="fusername" value="${user.username}">
                        </div>
                        <div class="form-group">
                            <label for="femail">邮箱地址</label>
                            <input type="email" class="form-control" onclick="check()" id="femail" value="${user.email}">
                            <span class="help-block"></span>
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>

                        </div>
                        <button id="updateBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button id="resetBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/jquery/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });

    $("#resetBtn").click(function () {
        $("#updateForm")[0].reset();
    });

    //显示校验结果的提示信息
    function show_validate_msg(ele, status, msg){
        //清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text(msg);
        if("success" == status){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else if("error" == status){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    function check(){
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
        var obj = document.getElementById("femail"); //要验证的对象
        if(obj.value === ""){ //输入不能为空
            show_validate_msg("#femail", "error", "邮箱不能为空！");
            //alert("输入不能为空!");
            return false;
        }else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
            show_validate_msg("#femail", "success", "邮箱格式错误！");
            return false;
        }else{
            //alert("通过！");
            return true;
        }
    }

    //校验用户名是否可用
    $("#floginacct").change(
        function() {
            //发送ajax请求校验用户名是否可用
            var loginacct = this.value;
            $.ajax({
                url : "${APP_PATH}/user/isExistedNickName.do",
                data : "loginacct=" + loginacct,
                type : "POST",
                success : function(result) {
                    if (result.code == 100) {
                        show_validate_msg("#floginacct", "success", "用户名可用！");
                        $("#updateBtn").attr("ajax-va", "success");
                    } else {
                        show_validate_msg("#floginacct", "账户已经存在！");
                        $("#updateBtn").attr("ajax-va", "error");
                    }
                }
            });
        });

    $("#updateBtn").click(function () {
        var floginacct = $("#floginacct");
        var fusername = $("#fusername");
        var femail = $("#femail");

        if(!check()){
            return false;

        }
        //判断之前的ajax用户名校验是否成功
        if($(this).attr("ajax-va") == "error"){
            return false;
        }else{
            show_validate_msg("#floginacct", "账户已经存在！");
        }

        $.ajax({
            type : "POST",
            data : {
                "loginacct" : floginacct.val(),
                "username" : fusername.val(),
                "email" : femail.val(),
                "id" : "${user.id}"
            },
            url : "${APP_PATH}/user/doUpdate.do",
            beforeSend : function () {
                return true;
            },
            success : function (result) {
                if(result.success){
                    window.location.href="${APP_PATH}/user/index.htm"
                }else {
                    layer.msg("修改用户失败！", {time: 1000, icon: 5, shift: 6});
                }
            },
            error : function () {
                layer.msg("修改失败！",{time:1000,icon:5,shift:6});
            }
        });
    });
</script>
</body>
</html>
