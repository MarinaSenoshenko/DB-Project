function showByDateAndSponsor() {
    let id = document.getElementById('id').value;
    if (id === 'all') {
        id = '';
    }
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/competition/byperiod/' + start_date + '/' + end_date + '/' + id;
}

function showByFacilityAndSport() {
    const id = document.getElementById('facilityid').value;
    let sport = document.getElementById('sport').value;
    if (sport === 'all') {
        sport = '';
    }
    location.href='/main/competition/byfacility/' + id + '/' + sport;
}