let map;
function initMap() {

    let lat = $('#lat').val();
    let lon = $('#lon').val();

    console.log("lon:"+lon + " lat:"+lat);
    let shopMarker = { lat: parseFloat(lat), lng: parseFloat(lon) };

    map = new google.maps.Map(document.getElementById("map"), {
        center: shopMarker,
        zoom: 13
    });

    let marker = new google.maps.Marker({position: shopMarker, map: map});
}