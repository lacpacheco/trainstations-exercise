<!DOCTYPE html>
<html>
<head>
<!-- <#include "header.ftl"> -->

</head>

<body>

<div class="container">

<div class="alert alert-info text-center" id="testDiv" role="alert">
    Shortest route finder (Stations available: A, B, C, D, E)
    <form method="get">
    <div>
        StationA <input type="text" class="sia" name="pointA">
    </div>
    <div>
        StationB <input type="text" class="sia" name="pointB">
    </div>
        <input type="submit" value="Submit">
    </form>
    ${message}
</div>
</div>
<script>
    $("#testDiv").append("scascascasca");
</script>

</body>
</html>
