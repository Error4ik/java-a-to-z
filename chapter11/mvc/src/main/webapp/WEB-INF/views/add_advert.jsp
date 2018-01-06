<%@ page language="java" pageEncoding="utf-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Add new Advert</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(loadBrand());
        $(loadCarBody());
        $(loadEngine());
        $(loadTransmission());
        $(loadDriveUnit());
        $(loadCity());

        function loadBrand() {
            $("#brand").empty();
            $.ajax({
                url: './carBrand',
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("brand").innerHTML +=
                            "<option value=" + val.name + ">" + val.name + "</option>"
                    });
                    loadModel();
                }
            });
        }

        function loadModel() {
            var brand = document.getElementById("brand").value;
            $("#model").empty();
            $.ajax({
                url: './carModel',
                data: {brand: brand},
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("model").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    });
                }
            });
        }

        function loadCarBody() {
            $("#body").empty();
            $.ajax({
                url: './carBody',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("body").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    })
                }
            });
        }

        function loadEngine() {
            $("#engine").empty();
            $.ajax({
                url: './engine',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("engine").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    })
                }
            });
        }

        function loadTransmission() {
            $("#transmission").empty();
            $.ajax({
                url: './transmission',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("transmission").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    })
                }
            });
        }

        function loadDriveUnit() {
            $("#driveUnit").empty();
            $.ajax({
                url: './driveUnit',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("driveUnit").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    })
                }
            });
        }

        function loadCity() {
            $("#city").empty();
            $.ajax({
                url: './city',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, val) {
                        document.getElementById("city").innerHTML += "<option value='" + val.name + "'>" + val.name + "</option>";
                    })
                }
            });
        }

    </script>
</head>
<body>
<div class="container">
    <form action="./create" method="post" enctype="multipart/form-data">
        <div class="form-group" style="align-content: center">
            <div class="col-md-10" style="padding-top: 50px">
                <div class="row" style="text-align: center">
                    <div class="col-md-10">
                        <label for="title">Заголовок</label>
                        <input type="text" class="form-control" id="title" name="title" required="required">
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-5">
                        <label for="brand">Марка</label>
                        <select class="form-control" id="brand" name="brand" onchange="loadModel()"></select>
                    </div>
                    <div class="col-md-5">
                        <label for="model">Модель</label>
                        <select class="form-control" id="model" name="model"></select>
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-5">
                        <label for="engine">Двигатель</label>
                        <select class="form-control" id="engine" name="engine"></select>
                    </div>
                    <div class="col-md-5">
                        <label for="transmission">Коробка</label>
                        <select class="form-control" id="transmission" name="transmission"></select>
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-5">
                        <label for="body">Кузов</label>
                        <select class="form-control" id="body" name="body"></select>
                    </div>
                    <div class="col-md-5">
                        <label for="driveUnit">Привод</label>
                        <select class="form-control" id="driveUnit" name="driveUnit"></select>
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-5">
                        <label for="yearOfIssue">Год выпуска</label>
                        <input type="number" class="form-control" step="any" id="yearOfIssue" name="yearOfIssue" required="required">
                    </div>
                    <div class="col-md-5">
                        <label for="mileage">Пробег</label>
                        <input type="number" class="form-control" step="any" id="mileage" name="mileage" required="required">
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-5">
                        <label for="city">Город</label>
                        <select class="form-control" id="city" name="city"></select>
                    </div>
                    <div class="col-md-5">
                        <label for="price">Цена</label>
                        <input type="number" class="form-control" step="any" id="price" name="price" required="required">
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="col-md-10">
                        <label for="description" style="font-weight: bold">Описание</label>
                        <textarea class="form-control" id="description" name="description" required="required"></textarea>
                    </div>
                </div>
                <label for="upFile">Выберите файл</label>
                <input type="file" name="upFile" id="upFile"><br/>
                <div class="form-group" style="margin-top: 20px">
                    <input type="submit" class="btn btn-info" onsubmit="checkValid()"/>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>