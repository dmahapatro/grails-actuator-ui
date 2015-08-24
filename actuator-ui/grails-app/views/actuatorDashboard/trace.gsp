<!DOCTYPE html>
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
            <li class="active">Dashboard</li>
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
                    <h3 class="box-title">HTTP Call Trace</h3>
                    <div class="box-tools">
                        %{--<div class="input-group" style="width: 150px;">
                            <input type="text" name="table_search" class="form-control input-sm pull-right" placeholder="Search">
                            <div class="input-group-btn">
                                <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                            </div>
                        </div>--}%
                    </div>
                </div><!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tbody><tr>
                            <th>#</th>
                            <th>Method</th>
                            <th>Path</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Status</th>
                            <th>Content Type</th>
                        </tr>
                        <g:each var="traceItem" in="${traceMap.trace}" status="id">
                            <tr>
                                <td>${id+1}</td>
                                <td><code>${traceItem.info.method}</code></td>
                                <td>${traceItem.info.path}</td>
                                <td>${new Date(traceItem.timestamp as Long).format('yyyy-MM-dd')}</td>
                                <td>${new Date(traceItem.timestamp as Long).format('HH:mm')}</td>
                                <td>
                                    <label class="label label-${traceItem.info.headers.response.status.startsWith('2') ? 'success' : 'danger'}">
                                        ${traceItem.info.headers.response.status}</label>
                                </td>
                                <td><code>${traceItem.info.headers.response."Content-Type"}</code></td>
                            </tr>
                        </g:each>
                        </tbody></table>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div>
    </div><!-- /.row (main row) -->
    </section><!-- /.content -->
</body>
</html>
