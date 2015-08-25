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
        <small>${appInfo.app?.version}
            <g:if test="${appInfo.app?.grailsVersion}"> (Grails Version: ${appInfo.app?.grailsVersion})</g:if>
        </small>
    </h1>

    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Beans</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
<!-- Small boxes (Stat box) -->
<!-- Main row -->
    <g:each var="appContext" in="${beansMap.beans}">
        <div class="row">
            <div class="col-xs-12">
                <div class="box box-success">
                    <div class="box-header">
                        <h3 class="box-title">Beans loaded to Context: ${appContext.context}</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <table id="beansTable" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Bean</th>
                                <th>Scope</th>
                                <th>Depends On</th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each var="beanItem" in="${appContext.beans}" status="id">
                                <tr>
                                    <td>${id + 1}</td>
                                    <td>
                                        <g:if test="${beanItem.bean.contains(/./)}">
                                            <span title="${beanItem.bean}">
                                                <code>${beanItem.bean.tokenize(/./)[-1]}</code>
                                            </span>
                                        </g:if>
                                        <g:else>
                                            <span title="Type: ${beanItem.type}"><code>${beanItem.bean}</code></span>
                                        </g:else>
                                    </td>
                                    <td>${beanItem.scope}</td>
                                    <td>
                                        <g:if test="${beanItem.dependencies}">
                                            <g:each var="dependency" in="${beanItem.dependencies}">
                                                <g:if test="${dependency.contains(/./)}">
                                                    <span title="${dependency}"><code>${dependency.tokenize(/./)[-1]}</code></span><br>
                                                </g:if>
                                                <g:else>
                                                    <span><code>${dependency}</code></span><br>
                                                </g:else>

                                            </g:each>
                                        </g:if>
                                    </td>
                                </tr>
                            </g:each>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>#</th>
                                <th>Bean</th>
                                <th>Scope</th>
                                <th>Depends On</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
    </g:each><!-- /.row (main row) -->
</section><!-- /.content -->
<g:javascript>
    $(function () {
        $("#beansTable").DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    });
</g:javascript>
</body>
</html>