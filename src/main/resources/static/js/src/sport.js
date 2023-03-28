function addSport() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('POST', '/sport?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sport';
}

function deleteSport() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('DELETE', '/sport?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sport';
}
