<!DOCTYPE html>
<html>
<head>
<!-- <#include "header.ftl"> -->

</head>

<body>

<div class="container">

<div class="alert alert-info text-center" id="testDiv" role="alert">
    Shortest path finder

    <form method="get">
    <div>
        <br>
        Start Station
        <select name="start">
            <option value=""></option>
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        <option value="E">E</option>
        </select>
        <br>
    </div>

    <div>
        End Station
        <select name="end">
            <option value=""></option>
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        <option value="E">E</option>
    </select>
    </div>
        <input type="submit" value="Submit">
    </form>
    <br>
    ${message}
</div>
</div>


</body>
</html>
