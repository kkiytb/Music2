<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- Left side column. contains the logo and sidebar -->
 <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${base}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">HEADER</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="${musicMenu} ${musicList} treeview">
          <a href="#"><i class="fa fa-link"></i> <span>音乐</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li class="${musicMenu}"><a href="${base}/music/player.do?page=1"><i class="fa fa-music"></i> <span>音乐列表</span></a></li>
            <li class="${musicList}"><a href="${base}/music/list.do"><i class="fa fa-music"></i> <span>音乐列表</span></a></li>
          </ul>
        </li>
        <li class="${userAdd} ${userMenu} treeview">
          <a href="#"><i class="fa fa-link"></i> <span>用户</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li class="${userMenu}"><a href="${base}/user/users.do?page=1"><i class="fa fa-users"></i> <span>用户管理</span></a></li>
            <li class="${userAdd}"><a href="${base}/user/add.do"><i class="fa fa-user-plus"></i> <span>添加用户</span></a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>