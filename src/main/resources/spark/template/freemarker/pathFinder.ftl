<!DOCTYPE html>
<html>
<head>
<!-- <#include "header.ftl"> -->

</head>

<body>

<div class="container">

<div class="alert alert-info text-center" id="testDiv" role="alert">
    Teste para ver se js resulta. ${message}
    <div>
        StationA <input type="text" class="sia" name="numero" placeholder="A">
    </div>
    <div>
        StationB <input type="text" class="sia" name="numero" placeholder="B">
    </div>
</div>
</div>
<script>
    $("#testDiv").append("scascascasca");
</script>

</body>
</html>
