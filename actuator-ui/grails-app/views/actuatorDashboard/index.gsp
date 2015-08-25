<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="actuator">
    <title>${info.app.name} | Admin</title>
</head>

<body>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            ${info.app?.name?.toUpperCase()}
            <small>${info.app?.version}<g:if test="${info.app?.grailsVersion}"> (Grails Version: ${info.app?.grailsVersion})</g:if></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <div class="row">
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-${health.status == 'UP' ? 'green' : 'red'}">
                    <div class="inner">
                        <h3>${health.status}</h3>
                        <p>Status</p>
                    </div>

                    <div class="icon">
                        <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="#" class="small-box-footer">
                        More info <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-yellow">
                    <div class="inner">
                        <h3>
                            ${metrics.uptimeHours}<sup style="font-size: 20px">hrs</sup>
                            ${metrics.uptimeMinutes}<sup style="font-size: 20px">min</sup>
                            ${metrics.uptimeSeconds}<sup style="font-size: 20px">sec</sup>
                        </h3>

                        <p>System Uptime</p>
                    </div>

                    <div class="icon">
                        <i class="ion ion-ios-pulse-strong"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-teal">
                    <div class="inner">
                        <h3>${metrics.threads}</h3>

                        <p>Threads</p>
                    </div>

                    <div class="icon">
                        <i class="ion ion-network"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-orange">
                    <div class="inner">
                        <h3>${metrics["httpsessions.active"]}</h3>
                        <p>Active HTTP Sessions</p>
                    </div>

                    <div class="icon">
                        <i class="fa fa-exchange"></i>
                    </div>
                    <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div><!-- ./col -->
        </div><!-- /.row -->
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-aqua">
                        <g:set var="osName" value="${env.systemProperties["os.name"]}"/>
                        <g:set var="osVersion" value="${env.systemProperties["os.version"]}"/>
                        <g:set var="osArch" value="${env.systemProperties["os.arch"]}"/>
                        <i class="fa fa-${osName?.toLowerCase()?.startsWith("windows") ? "windows" : osName?.toLowerCase()?.startsWith("mac") ? "apple" : "linux"}"></i>
                    </span>
                    <div class="info-box-content">
                        <span class="info-box-text">${osName}</span>
                        <span class="info-box-number">${osVersion}</span>
                        <span class="">${osArch}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-green"><i class="fa fa-coffee"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">Java</span>
                        <span class="info-box-number">${env.systemProperties["java.version"]}</span>
                        <span class="">${env.systemProperties["java.vendor"]}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->

        <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>

            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-yellow"><i class="ion ion-earth"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">${env.systemProperties["user.country"]}</span>
                        <span class="info-box-number">${env.systemProperties["user.timezone"]}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="info-box">
                    <span class="info-box-icon bg-teal"><i class="ion ion-ios-gear"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">PID</span>
                        <span class="info-box-number">${env.systemProperties["PID"]}</span>
                    </div><!-- /.info-box-content -->
                </div><!-- /.info-box -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        <g:if test="${metrics.countersByStatus}">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-success">
                        <div class="box-header">
                            <h3 class="box-title">HTTP Call Statistics</h3>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>XHR Resource</th>
                                    <th>Response Status</th>
                                    <th>No. of Calls</th>
                                    <th>Response Time for last call (<strong>ms</strong>)</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each var="counter" in="${metrics.countersByStatus}" status="id">
                                    <tr>
                                        <td>${id + 1}</td>
                                        <td><code>${counter.name}</code></td>
                                        <td><label class="label label-${counter.status?.startsWith('2') ? 'success' : 'danger'}">${counter.status}</label></td>
                                        <td>${counter.value}</td>
                                        <td>${counter.gauge}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>#</th>
                                    <th>XHR Resource</th>
                                    <th>Response Status</th>
                                    <th>No. of Calls</th>
                                    <th>Response Time for last call (<strong>ms</strong>)</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div><!-- /.box-body -->
                    </div>
                </div>
            </div>
        </g:if>
        <div class="row">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="box box-danger">
                    <div class="box-header with-border">
                        <h3 class="box-title">Memory (KB)</h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <input id="freeMemory" hidden value="${metrics["mem.free"]}">
                        <input id="memory" hidden value="${metrics.mem}">
                        <canvas id="memoryChart" style="height: 300px; width: 687px;" width="687" height="343"></canvas>
                    </div><!-- /.box-body -->
                </div>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="box box-danger">
                    <div class="box-header with-border">
                        <h3 class="box-title">Heap (KB)</h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <input id="heap" hidden value="${metrics.heap}">
                        <input id="heapUsed" hidden value="${metrics["heap.used"]}">
                        <canvas id="heapChart" style="height: 300px; width: 687px;" width="687" height="343"></canvas>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section><!-- /.content -->
    <g:javascript>
        $(function(){
            var memory = parseInt($("#memory").val()),
                freeMemory = parseInt($("#freeMemory").val()),
                usedMemory = memory - freeMemory;

            var heap = parseInt($("#heap").val()),
                heapUsed = parseInt($("#heapUsed").val()),
                heapFree = heap - heapUsed;

            var pieOptions = {
                  //Boolean - Whether we should show a stroke on each segment
                  segmentShowStroke: true,
                  //String - The colour of each segment stroke
                  segmentStrokeColor: "#fff",
                  //Number - The width of each segment stroke
                  segmentStrokeWidth: 1,
                  //Number - The percentage of the chart that we cut out of the middle
                  percentageInnerCutout: 50, // This is 0 for Pie charts
                  //Number - Amount of animation steps
                  animationSteps: 100,
                  //String - Animation easing effect
                  animationEasing: "easeOutBounce",
                  //Boolean - Whether we animate the rotation of the Doughnut
                  animateRotate: true,
                  //Boolean - Whether we animate scaling the Doughnut from the centre
                  animateScale: false,
                  //Boolean - whether to make the chart responsive to window resizing
                  responsive: true,
                  // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
                  maintainAspectRatio: false,
                  //String - A tooltip template
                  //tooltipTemplate: "<%@ label %>: <%@ value %>"
            };

            function buildDonutChart(element, inputs) {
                //-------------
                //- PIE CHART -
                //-------------
                // Get context with jQuery - using jQuery's .get() method.
                var pieChartCanvas = $(element).get(0).getContext("2d");
                var pieChart = new Chart(pieChartCanvas);
                var PieData = [
                  {
                    value: inputs[0].value,
                    color: "#f56954",
                    highlight: "#f56954",
                    label: inputs[0].label
                  },
                  {
                    value: inputs[1].value,
                    color: "#00a65a",
                    highlight: "#00a65a",
                    label: inputs[1].label
                  }
                ];

                //Create pie or douhnut chart
                // You can switch between pie and douhnut using the method below.
                pieChart.Doughnut(PieData, pieOptions);
                //-----------------
                //- END PIE CHART -
                //-----------------
              }

                buildDonutChart(
                    "#memoryChart",
                    [{value: usedMemory, label: "Used"}, {value: freeMemory, label: "Free"}]
                );

                buildDonutChart(
                    "#heapChart",
                    [{value: heapUsed, label: "Used"}, {value: heapFree, label: "Free"}]
                );
        });

        $('#example2').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
    </g:javascript>
</body>
</html>
