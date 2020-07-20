<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <asset:stylesheet href="gmap.css"/>

    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=${googleAPIKey}&callback=initMap&libraries=&v=weekly" defer></script>

    <asset:javascript src="gmap.js"/>

    <title>Dive-Shop</title>
</head>
<body>
    <form>
        <g:hiddenField name="lon" value="${diveShop.lon}" />
        <g:hiddenField name="lat" value="${diveShop.lat}" />
    </form>

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
                <li class="nav-item">
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
                <div class="card">
                    <div class="card-body">
                        <div class="row text-center">
                            <div class="col">
                                <g:if test="${image1Exist}">
                                    <img src="${createLink(action:"showImage1", id: diveShop.id)}"
                                         class="card-img-top" alt="thumbnail-1">
                                </g:if>
                                <g:else>
                                    <asset:image src="dummy.jpg" />
                                </g:else>
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col">
                                <h2>${diveShop.name}</h2>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col border bg-light">
                                <p>Jalan Bangka Raya No. 39A (Pela Mampang)</p>
                                <p>+62987654321</p>
                                <p>info@divers.com</p>
                                <p>http://www.masterselam.com</p>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col">
                                <fieldset>
                                    <legend>Operational Hours:</legend>
                                    <g:each in="${operationalDays}" var="opDay">
                                        <div class="row">
                                            <div class="col text-left">${opDay.dayLabel}</div>
                                            <g:if test="${opDay.closed}">
                                                Closed
                                            </g:if>
                                            <g:else>
                                                <div class="col text-right">${opDay.openHour} to ${opDay.closeHour}</div>
                                            </g:else>
                                        </div>
                                    </g:each>
                                </fieldset>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col">
                                <fieldset>
                                    <legend>Features:</legend>
                                    <g:each in="${features}" var="feature">
                                        <div class="row">
                                            <div class="col-1">-</div>
                                            <div class="col">${feature.label}</div>
                                        </div>
                                    </g:each>
                                </fieldset>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col">
                                <fieldset>
                                    <legend>Courses:</legend>
                                    <g:each in="${courses}" var="course">
                                        <div class="row">
                                            <div class="col-1">x</div>
                                            <div class="col">${course.label}</div>
                                        </div>
                                    </g:each>
                                </fieldset>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-8">
                <div id="map"></div>
                <!--
                <div class="card">
                    <div class="card-body">
                        <div id="map"></div>
                    </div>
                </div>
                -->
            </div>

        </div>
    </div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>