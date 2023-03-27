function showSportClubByPeriod() {
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/sportclub/byperiod/' + start_date + '/' + end_date;
}

function addSportClub() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('POST', '/addsportclub?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportclub';
}