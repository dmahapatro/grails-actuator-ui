<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="actuator">
</head>

<body>
<section class="content">
    <div class="error-page">
        <h2 class="headline text-red">${params.error}</h2>
        <div class="error-content" style="padding-top: 15px">
            <h3><i class="fa fa-warning text-red"></i> ${params.errorDescription}</h3>
            <div>${params.errorDetail}</div>
        </div>
    </div><!-- /.error-page -->
</section>
</body>
</html>
