function showByDateAndSponsor() {
    var id = document.getElementById('id').value;
    if (id === 'all') {
        id = '';
    }
    var start_date = document.getElementById('start_date').value;
    var end_date = document.getElementById('end_date').value;

    location.href='/main/competition/byperiod/' + start_date + '/' + end_date + '/' + id;
}

function showByFacilityAndSport() {
    var id = document.getElementById('facilityid').value;
    var sport = document.getElementById('sport').value;
    if (sport === 'all') {
        sport = '';
    }
    location.href='/main/competition/byfacility/' + id + '/' + sport;
}