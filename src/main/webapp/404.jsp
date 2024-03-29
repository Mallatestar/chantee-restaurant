<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="view/css/404.css">
</head>
<body>
<p class="blockText" style="font-family: Arial,serif; font-size: 30px" ><fmt:message key="error_404"/> </p>

<div class="ag-page-404">
    <div class="ag-toaster-wrap">
        <div class="ag-toaster">
            <div class="ag-toaster_back"></div>
            <div class="ag-toaster_front">
                <div class="js-toaster_lever ag-toaster_lever"></div>
            </div>
            <div class="ag-toaster_toast-handler">
                <div class="ag-toaster_shadow"></div>
                <div class="js-toaster_toast ag-toaster_toast js-ag-hide"></div>
            </div>
        </div>

        <canvas id="canvas-404" class="ag-canvas-404"></canvas>
        <img class="ag-canvas-404_img" src="https://raw.githubusercontent.com/SochavaAG/example-mycode/master/pens/404-error-smoke-from-toaster/images/smoke.png">
    </div>
</div>
<!-- jQuery -->
<script type="text/javascript" src="view/js/jquery.min.js"></script>
<script type="text/javascript" src="view/js/404.js"></script>
</body>
</html>
</fmt:bundle>