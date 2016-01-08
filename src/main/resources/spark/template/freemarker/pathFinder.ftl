<!DOCTYPE html>
<html>
<head>
<#include "header.ftl">

</head>

<body>

<div class="container">

    <div class="alert alert-info" id="pathList" style="float:right;width: 20%;padding: 14px;">List of paths added</div>
    <div class="alert alert-info" id="stationList" style="float:right;margin-right: 40px;width: 20%;padding: 14px;">List of stations added</div>
    <div class="alert alert-info" id="testDiv" role="alert">
        <h1>Shortest path finder</h1>

        <div style="width:50%;">
            <div id="addStation">
                <span><input type="text" id="stationName"/></span>
                <button id="inputStationName">Submit</button>
            </div>
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
                <button id="inputStationName">Submit</button>
            </div>

            <form method="post">
                <div>
                    <br>
                    Start Station
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
                    End Station
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
        ${message}
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
