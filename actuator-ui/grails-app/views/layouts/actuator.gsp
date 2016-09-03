<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><g:meta name="info.app.name"/> | Admin</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"></link>

    <asset:stylesheet href="actuator-ui/AdminLTE.css"></asset:stylesheet>
    <asset:javascript src="actuator-ui/plugins/jQuery/jQuery-2.1.4.min.js"></asset:javascript>
    <asset:javascript src="actuator-ui/plugins/datatables/jquery.dataTables.js"></asset:javascript>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="http://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <g:layoutHead/>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <g:link action="index" class="logo">
            <span class="logo-mini"><b class="fa fa-bolt"></b></span>
            <span class="logo-lg"><b><i class="fa fa-bolt"></i> Admin</b></span>
        </g:link>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="user">
                        <plugin:isNotAvailable name="spring-security-core">
                            <p class="navbar-text">
                                <i class="fa fa-warning text-red"></i>
                                <span style="color: white">Spring Security not installed! (UNSECURED)</span>
                            </p>
                        </plugin:isNotAvailable>
                    </li>
                    <plugin:isAvailable name="console">
                        <li class="messages-menu">
                            <g:link controller="console"
                                    action="index"
                                    plugin="console"
                                    title="Open Console"
                                    target="_blank" aria-expanded="false">
                                <i class="fa fa-edit"></i>
                            </g:link>
                        </li>
                    </plugin:isAvailable>
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <actuator:avatar default="actuator-ui/avatar5.png" cssClass="user-image" alt="User Image"/>

                            <span class="hidden-xs">
                                <plugin:isAvailable name="spring-security-core">
                                    <sec:ifLoggedIn><sec:username/></sec:ifLoggedIn>
                                    <sec:ifNotLoggedIn>Anonymous</sec:ifNotLoggedIn>
                                </plugin:isAvailable>
                                <plugin:isNotAvailable name="spring-security-core">
                                    Anonymous
                                </plugin:isNotAvailable>
                            </span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                            <actuator:avatar default="actuator-ui/avatar5.png" cssClass="img-circle" alt="User Image"/>
                                <p>
                                    <plugin:isAvailable name="spring-security-core">
                                        <sec:ifLoggedIn><sec:username/></sec:ifLoggedIn>
                                        <sec:ifNotLoggedIn>Anonymous</sec:ifNotLoggedIn>
                                    </plugin:isAvailable>
                                    <plugin:isNotAvailable name="spring-security-core">
                                        Anonymous
                                    </plugin:isNotAvailable>
                                </p>
                            </li>
                            <li class="user-footer">
                                <div class="pull-right">
                                    <plugin:isAvailable name="spring-security-core">
                                        <sec:ifLoggedIn>
                                            <form name="logout" method="POST" action="${createLink(controller:'logout') }">
                                                <input type="submit" class="btn btn-default btn-flat" value="Sign out">
                                            </form>
                                        </sec:ifLoggedIn>
                                    </plugin:isAvailable>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <actuator:avatar default="actuator-ui/avatar5.png" cssClass="img-circle" alt="User Image"/>
                </div>

                <div class="pull-left info">
                    <p>
                        <plugin:isAvailable name="spring-security-core">
                            <sec:ifLoggedIn><sec:username/></sec:ifLoggedIn>
                            <sec:ifNotLoggedIn>Anonymous</sec:ifNotLoggedIn>
                        </plugin:isAvailable>
                        <plugin:isNotAvailable name="spring-security-core">
                            Anonymous
                        </plugin:isNotAvailable>
                    </p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">MAIN NAVIGATION</li>
                <!-- TODO: Quick and dirty work around to set the clicked item as active. Fix me. -->
                <li class="${actionName == 'index' ? 'active' : ''}">
                    <g:link uri="/actuator/dashboard">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    </g:link>
                </li>
                <li class="${actionName == 'traceability' ? 'active' : ''}">
                    <g:link uri="/actuator/traceability">
                        <i class="fa fa-tasks"></i> <span>Traceability</span>
                        <span class="label label-primary pull-right">HTTP</span>
                    </g:link>
                </li>
                <li class="${actionName == 'springBeans' ? 'active' : ''}">
                    <g:link uri="/actuator/beans">
                        <i class="fa fa-cubes"></i> <span>Beans</span>
                    </g:link>
                </li>
                <li class="${actionName == 'allMappings' ? 'active' : ''}">
                    <g:link uri="/actuator/mappings">
                        <i class="fa fa-map-signs"></i> <span>Mappings</span>
                    </g:link>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <g:meta name="info.app.name"/>
                <small><g:meta name="info.app.version"/>
                (Grails Version: <g:meta name="info.app.grailsVersion"/>)
                </small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            </ol>
        </section>

        <g:layoutBody/>
    </div>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> <g:meta name="info.app.version"/>
        </div>
        <strong>Copyright &copy; 2016 </strong>
    </footer>
</div>

<asset:javascript src="actuator-ui/app.js"></asset:javascript>
</body>
</html>
