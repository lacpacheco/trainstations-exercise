<!DOCTYPE html>
<html>
<head>
<!-- <#include "header.ftl"> -->

</head>

<body>

<div class="container">

<div class="alert alert-info text-center" id="testDiv" role="alert">
    Shortest route finder
    <form method="get">
    <div>
        Start Station
        <select name="start">
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        <option value="E">E</option>
        </select>
    </div>
    <div>
        End Station
        <select name="end">
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        <option value="E">E</option>
    </select>
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
