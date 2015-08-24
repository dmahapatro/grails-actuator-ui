<%@ page contentType="text/html;charset=UTF-8" %>
<g:set var="appInfo" value="${session.appInfo}"/>

<html>
<head>
    <meta name="layout" content="actuator" charset="utf-8">
    <title>${appInfo.app.name} | Admin</title>
</head>

<body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            ${appInfo.app?.name?.toUpperCase()}
            <small>${appInfo.app?.version}<g:if test="${appInfo.app?.grailsVersion}"> (Grails Version: ${appInfo.app?.grailsVersion})</g:if></small>
        </h1>

        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Mappings</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <!-- Main row -->
        <div class="row">
            <div class="col-xs-12">
                <div class="box box-success">
                    <div class="box-header">
                        <h3 class="box-title">Request Mappings</h3>
                        <div class="box-tools">
                        </div>
                    </div><!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <th>#</th>
                                <th>URI</th>
                                <th>Bean</th>
                            </tr>
                            <g:each var="mappingItem" in="${mappingsMap}" status="id">
                                <tr>
                                    <td>${id + 1}</td>
                                    <td><code>${mappingItem.key}</code></td>
                                    <td><code>${mappingItem.value.bean}</code></td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div><!-- /.row (main row) -->
    </section><!-- /.content -->
</body>
</html>