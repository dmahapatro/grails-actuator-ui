<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><g:layoutTitle default="Actuator UI"/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    %{--<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">--}%
    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <asset:stylesheet src="actuator-ui/AdminLTE.css"></asset:stylesheet>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="http://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <g:layoutHead/>
</head>
<body>
<g:layoutBody/>
<!-- jQuery 2.1.4 -->
%{--<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>--}%
<!-- jQuery UI 1.11.4 -->
<asset:javascript src="actuator-ui/plugins/ckeditor/ckeditor.js"></asset:javascript>
<asset:javascript src="actuator-ui/plugins/jQuery/jQuery-2.1.4.min.js"></asset:javascript>
<asset:javascript src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></asset:javascript>
<asset:javascript src="actuator-ui/app.js"></asset:javascript>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<asset:script>
    $(function () {
        $.widget.bridge('uibutton', $.ui.button);

        $("#example1").DataTable();
        $('#example2').DataTable({
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      });
</asset:script>
<!-- Bootstrap 3.3.5 -->
%{--<script src="bootstrap/js/bootstrap.min.js"></script>--}%
<!-- Morris.js charts -->
<asset:javascript src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></asset:javascript>
%{--<script src="plugins/morris/morris.min.js"></script>--}%
<!-- Sparkline -->
%{--<script src="plugins/sparkline/jquery.sparkline.min.js"></script>--}%
<!-- jvectormap -->
%{--<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>--}%
%{--<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>--}%
<!-- jQuery Knob Chart -->
%{--<script src="plugins/knob/jquery.knob.js"></script>--}%
<!-- daterangepicker -->
<asset:javascript src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></asset:javascript>
%{--<script src="plugins/daterangepicker/daterangepicker.js"></script>--}%
<!-- datepicker -->
%{--<script src="plugins/datepicker/bootstrap-datepicker.js"></script>--}%
<!-- Bootstrap WYSIHTML5 -->
%{--<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>--}%
<!-- Slimscroll -->
%{--<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>--}%
<!-- FastClick -->
%{--<script src="plugins/fastclick/fastclick.min.js"></script>--}%
<!-- AdminLTE App -->
%{--<script src="dist/js/app.min.js"></script>--}%
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
%{--<script src="dist/js/pages/dashboard.js"></script>--}%
<!-- AdminLTE for demo purposes -->
%{--<script src="dist/js/demo.js"></script>--}%

</body>
</html>
