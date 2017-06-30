<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/17
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登陆</title>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <style>

    </style>
</head>

<body>
<div class="container-fluid">
    <!--
        作者：305848109@qq.com
        时间：2017-05-16
        描述： 标题
    -->
    <div class="row title">
        <div class="col-md-3 col-md-offset-4">
            HONDA_CMS
        </div>
    </div>
    <div class="row">
        <!--
            作者：305848109@qq.com
            时间：2017-05-16
            描述：用户名密码等输入项
        -->
        <div class="col-md-3 col-md-offset-4">
            <form action="login" method="post" class="form-horizontal" id="login">
                <%--用户名--%>
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label"><i class="glyphicon glyphicon-user"></i></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="username" name="username" required autofocus
                               autocomplete="off" placeholder="请输入用户名">
                    </div>
                </div>
                <%--密码--%>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label"><i
                            class="glyphicon glyphicon-lock"></i></label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password" name="password" required
                               placeholder="请输入密码">
                    </div>
                </div>
                <%--其他输入项--%>
                <div class="form-group">
                    <div class="checkbox col-sm-3 col-sm-offset-4" style="color: #999999;">
                        <input type="checkbox" name="rememberUsername" value="rememberUsername">记住用户名
                    </div>
                    <div class="col-sm-5 text-right" style="padding-top: 7px">
                        <button class="showLayer">忘记密码</button>
                        <button class="showLayer" data-toggle="modal" data-target="#myModal">注册</button>
                    </div>
                </div>
            </form>
        </div>
        <!--
            作者：305848109@qq.com
            时间：2017-05-16
            描述：登陆按钮
        -->
        <div>
            <input type="submit" class="loginbtn" value="登 陆" form="login"/>
        </div>
    </div>


    <!--
        作者：305848109@qq.com
        时间：2017-05-17
        描述：登陆弹窗
    -->
    <div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h3 class="modal-title">管理员注册
                        <small> USER REGISTER</small>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <form class="form-horizontal" role="form" action="addUser" method="post" id="register">
                            <!--用户名-->
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="username" placeholder="6~18位字母或数字"
                                           required autocomplete="off" pattern="^[A-Za-z][A-Za-z0-9]{5,17}">
                                </div>
                                <div class="col-sm-3">
                                    <span id="checkInfo" style="line-height: 34px"></span>
                                </div>
                            </div>
                            <!--密码-->
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" placeholder="6~12位字符" name="password"
                                           required minlength="6" maxlength="12">
                                </div>
                            </div>
                            <!--工号-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工号</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="workid" placeholder="请输入工号" required>
                                </div>
                            </div>
                            <!--真实姓名-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">真实姓名</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="realname" placeholder="请输入真实姓名"
                                           required pattern="^[\u4E00-\u9FA5A-Za-z]+$">
                                </div>
                            </div>
                            <!--手机-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机</label>
                                <div class="input-group col-sm-7" style="padding: 0 15px;">
                                    <span class="input-group-addon">+86</span>
                                    <input type="tel" class="form-control" name="tel" placeholder="请输入手机号码" required
                                           pattern="^1(3|4|5|7|8)\d{9}$">
                                </div>
                            </div>
                            <!--验证码-->
                            <div class="form-group">
                                <label class="col-sm-2  control-label">验证码</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" name="verifycode" placeholder="验证码">
                                </div>
                                <div class="col-sm-4">
                                    <img src="verify" id="verifycodeImg"/>
                                </div>
                                <div class="col-sm-3">
                                    <span id="verifyInfo"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary" value="注 册" form="register" id="register_btn"/>
                    <input type="reset" class="btn btn-warning" value="重 置" form="register"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/index.js"></script>
</body>

</html>
