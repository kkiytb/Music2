<%@ page contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" scope="request" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${base}/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${base}/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${base}/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${base}/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="${base}/dist/css/skins/skin-blue.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- 导入导航栏 -->
  <c:import url="/WEB-INF/inc/header.jsp"></c:import>
  <!-- 导入菜单栏 -->
  <c:import url="/WEB-INF/inc/leftside.jsp"></c:import>

  <!-- 内容区域 -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        用户管理
        <small>管理用户信息</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

      <!--------------------------
        | 在这里写内容 |
        -------------------------->

        <div class="row">
          <div class="col-md-12">
            <div class="box">
              <div class="box-header with-border">
                <h3 class="box-title">修改用户信息</h3>
              </div>
              <!-- /.box-header -->
              <div class="box-body">
	                         <!-- 表单  -->
                <!-- form start -->
                <form class="form-horizontal"
                method="post" action="${base}/user/update.do">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">用户名</label><c:out value="${error}"></c:out>
                        <input type="hidden" name="id" value="${id}">
                      <div class="col-sm-10">
                        <input type="text" value="${user.username}" class="form-control" id="inputEmail3" placeholder="请输入用户名" name="username">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    
                      <div class="col-sm-10">
                        <input type="text" value="${user.password}" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputConfirm" class="col-sm-2 control-label">确认密码</label>
    
                      <div class="col-sm-10">
                        <input type="text" value="${user.password}" class="form-control" id="inputConfirm" placeholder="请再次确认密码" name="confirm">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputEmail" class="col-sm-2 control-label">邮箱</label>
    
                      <div class="col-sm-10">
                        <input type="text" value="${user.email}" class="form-control" id="inputEmail" placeholder="请输入邮箱" name="email">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputMobile" class="col-sm-2 control-label">手机号</label>
    
                      <div class="col-sm-10">
                        <input type="text" value="${user.mobile}" class="form-control" id="inputMobile" placeholder="请输入手机号码" name="mobile">
                      </div>
                    </div>
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <button type="submit" class="btn btn-info pull-right">保存</button>
                  </div>
                  <!-- /.box-footer -->
                </form>
                           
              </div>
              <!-- /.box-body -->
            </div>
            <!-- /.box -->
          </div>
       </div>



    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- 版权信息 -->
  <c:import url="/WEB-INF/inc/footer.jsp"></c:import>

  <!-- 通知区域 -->
  <c:import url="/WEB-INF/inc/sidebar.jsp"></c:import>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="${base}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${base}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${base}/dist/js/adminlte.min.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>
