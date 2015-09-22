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
        <div class="row">
            <div class="col-xs-12">
                <div class="box box-success">
                    <div class="box-header">
                        <h3 class="box-title">Request Mappings</h3>
                        <div class="box-tools">
                            <div class="input-group" style="width: 150px;">
                                <input type="text"
                                       name="table_search"
                                       id="mappingSearch"
                                       class="form-control input-sm pull-right" placeholder="Search">
                            </div>
                        </div>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <table id="mappingsTable" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>URI</th>
                                <th>Bean</th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each var="mappingItem" in="${mappingsMap}" status="id">
                                <tr>
                                    <td>${id + 1}</td>
                                    <td><code>${mappingItem.key}</code></td>
                                    <td><code>${mappingItem.value.bean}</code></td>
                                </tr>
                            </g:each>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>#</th>
                                <th>URI</th>
                                <th>Bean</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div><!-- /.row (main row) -->
    </section><!-- /.content -->
</body>
</html>