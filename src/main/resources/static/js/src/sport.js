function addSport() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('POST', '/addsport?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sport';
}