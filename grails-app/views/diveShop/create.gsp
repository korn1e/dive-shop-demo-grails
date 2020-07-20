<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <asset:stylesheet href="choices.css"/>


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>

    <asset:javascript src="choices.min.js"/>

    <title>Dive-Shop</title>
</head>
    <body>
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <g:link action="index" class="nav-link">
                                Home
                            </g:link>
                        </li>
                        <li class="nav-item active">
                            <g:link action="create" class="nav-link">
                                New Shop
                            </g:link>
                        </li>
                    </ul>
                </div>
            </nav>

            <br/>

            <div class="row">
                <div class="col-4">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">New Shop</a>
                        <g:each in="${allDiveShops}" var="shop">
                            <g:link action="show" id="${shop.id}" class="list-group-item list-group-item-action">
                                ${shop.name}
                            </g:link>
                        </g:each>
                    </div>
                </div>
                <div class="col-8">
                    <div class="card">
                        <div class="card-body">
                            <g:uploadForm controller="diveShop" action="save" >
                                <div class="form-group row">
                                    <label for="langIso" class="col-sm-2 col-form-label">Languages</label>
                                    <div class="col-sm-10">
                                        <g:select class="form-control"
                                                  name="langIso"
                                                  from="${languages}"
                                                  optionValue="lang"
                                                  optionKey="langIso"
                                                  value="${diveShop.langIso}"
                                                  noSelection="${['null':'Select One...']}"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                                    <div class="col-sm-10">
                                        <g:textField name="name" value="${diveShop.name}" class="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-sm-2"></div>
                                    <div class="col-sm-5">
                                        <div class="card" style="width: 18rem;">
                                            <asset:image src="dummy.jpg" alt="thumbnail-1" />
                                            <div class="card-body">
                                                <label for="image1"></label>
                                                <input type="file" class="form-control-file" id="image1" name="image1">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-sm-5">
                                        <div class="card" style="width: 18rem;">
                                            <asset:image src="dummy.jpg" alt="thumbnail-2"/>
                                            <div class="card-body">
                                                <label for="image2"></label>
                                                <input type="file" class="form-control-file" id="image2" name="image2">
                                            </div>
                                        </div>

                                    </div>
                                </div>


                                <div class="form-group row">
                                    <label for="address1" class="col-sm-2 col-form-label">Address 1</label>
                                    <div class="col-sm-10">
                                        <g:textField name="address1" value="${diveShop.address1}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="address2" class="col-sm-2 col-form-label">Address 2</label>
                                    <div class="col-sm-10">
                                        <g:textField name="address2" value="${diveShop.address2}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="address3" class="col-sm-2 col-form-label">Address 3</label>
                                    <div class="col-sm-10">
                                        <g:textField name="address3" value="${diveShop.address3}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="postalCode" class="col-sm-2 col-form-label">Postal Code</label>
                                    <div class="col-sm-10">
                                        <g:textField name="postalCode" value="${diveShop.postalCode}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="phone" class="col-sm-2 col-form-label">Telp./Hp</label>
                                    <div class="col-sm-10">
                                        <g:textField name="phone" value="${diveShop.phone}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <g:textField name="email" value="${diveShop.email}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="website" class="col-sm-2 col-form-label">Website</label>
                                    <div class="col-sm-10">
                                        <g:textField name="website" value="${diveShop.website}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lat" class="col-sm-2 col-form-label">Latitude</label>
                                    <div class="col-sm-10">
                                        <g:textField name="lat" value="${diveShop.lat}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lon" class="col-sm-2 col-form-label">Longitude</label>
                                    <div class="col-sm-10">
                                        <g:textField name="lon" value="${diveShop.lon}" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="region" class="col-sm-2 col-form-label">Region</label>
                                    <div class="col-sm-10">
                                        <g:textField name="region" value="${diveShop.region}" class="form-control"/>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col">
                                        Operational Hours
                                    </div>
                                </div>

                                <g:each in="${operationalDays}" var="opDay">
                                    <div class="form-group row">
                                        <label for="day${opDay.dayIndex}Start" class="col-sm-2 col-form-label">${opDay.dayLabel}</label>
                                        <div class="col-sm-2">
                                            <g:textField name="day${opDay.dayIndex}Start" value="${opDay.openHour}" class="form-control"/>
                                        </div>
                                        <div class="col-1">
                                            -
                                        </div>
                                        <div class="col-sm-2">
                                            <g:textField name="day${opDay.dayIndex}End" value="${opDay.closeHour}" class="form-control"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-check">
                                                <g:checkBox class="form-check-input"
                                                            name="day${opDay.dayIndex}Close"
                                                            value="${opDay.closed}" />
                                                <label class="form-check-label" for="day${opDay.dayIndex}Close">
                                                    Closed
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </g:each>

                                <div class="form-group row">
                                    <label for="features" class="col-sm-2 col-form-label">Features</label>
                                    <div class="col-sm-10">
                                        <g:textField name="features" value="${diveShop.features}" placeholder="Enter something" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="courses" class="col-sm-2 col-form-label">Courses</label>
                                    <div class="col-sm-10">
                                        <g:textField name="courses" value="${diveShop.courses}" placeholder="Enter something" class="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <input type="submit" class="btn btn-primary" value="Create" />
                                    </div>
                                </div>
                            </g:uploadForm>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->

        <script>
            var featuresUniqueVals = new Choices('#features', {
                paste: false,
                duplicateItemsAllowed: false,
                editItems: true,
                removeItemButton: true,
            });

            var courseUniqueVals = new Choices('#courses', {
                paste: false,
                duplicateItemsAllowed: false,
                editItems: true,
                removeItemButton: true,
            });
        </script>

    </body>
</html>