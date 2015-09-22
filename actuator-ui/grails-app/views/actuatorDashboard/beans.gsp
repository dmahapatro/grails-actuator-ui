<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="actuator" charset="utf-8">
</head>

<body>
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
                        <div class="box-tools">
                            <div class="input-group" style="width: 150px;">
                                <input type="text"
                                       name="table_search"
                                       id="beanSearch"
                                       class="form-control input-sm pull-right" placeholder="Search">
                            </div>
                        </div>
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
</body>
</html>