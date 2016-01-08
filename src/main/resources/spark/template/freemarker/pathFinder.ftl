<!DOCTYPE html>
<html>
<head>
<#include "header.ftl">
<title>Shortest Route Finder</title>
</head>

<body>

<div class="container">

    <div class="alert alert-info" id="pathList" style="float:right;width: 20%;padding: 14px;">
        <h4>List of routes</h4>
        A-B, 3 minutes<br>
        B-A, 3 minutes<br>
        A-D, 6 minutes<br>
        B-C, 7 minutes<br>
        C-D, 8 minutes<br>
        D-E, 9 minutes<br>
        E-D, 9 minutes<br>
        D-C, 9 minutes<br>
        D-B, 5 minutes<br>
        C-E, 3 minutes
    </div>
    <div class="alert alert-info" id="stationList" style="float:right;margin-right: 40px;width: 20%;padding: 14px;">
        <h4>List of stations</h4>
        A<br>
        B<br>
        C<br>
        D<br>
        E<br>

    </div>
    <div class="alert alert-info" id="testDiv" role="alert"  style="min-height: 400px;">
        <h1>Shortest Route Finder</h1>

        <div style="width:50%;">
            <div style="display: none;">
            <form>
            <div id="addStation">
                <span><input type="text" id="stationName" style="width:120px;"/>
                <input type="submit" id="inputStationName"></input></span>
            </div>
            </form>
            <form>
            <div id="addPath">
                <span>
                    <select name="addStart" id="addStart">
                    <option value=""></option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                    <option value="E">E</option>
                    </select>
                    <select name="addEnd" id="addEnd">
                        <option value=""></option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                        <option value="E">E</option>
                    </select>
                    <input id="weight" style="width:40px;" type="number"/>
                </span>
                <input type="submit" id="inputStationName"></input>
            </div>
            </form>
            </div>

            <form method="post">
                <div>
                    <br>
                    <span>Start Station</span>
                    <select name="start" id="start">
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
                    <span>End Station</span>
                    <select name="end" id="end">
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
            <br>
            <br>
        <h3>${message}</h3>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $("#inputStationName").click(function(){
            $("#stationList").append("<div>"+$("#stationName").val()+"</div>");
            $("#addStart").append("<option value="+$("#stationName").val()+">"+$("#stationName").val()+"</option>");
            $("#addEnd").append("<option value="+$("#stationName").val()+">"+$("#stationName").val()+"</option>");
            $("#start").append("<option value="+$("#stationName").val()+">"+$("#stationName").val()+"</option>");
            $("#end").append("<option value="+$("#stationName").val()+">"+$("#stationName").val()+"</option>");
            $("#stationName").val("");
        });
    });
</script>

</body>
</html>
