function addSport() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('POST', '/sport?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sport');
}

function deleteSport() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        sport: sport,
    });

    req.open('DELETE', '/sport?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sport');
}

function changeState(req, params, ref) {
    return new Promise((resolve, reject) => {
        req.onreadystatechange = () => {
            if (req.readyState === 4) {
                if (req.status === 200) {
                    location.href=ref;
                } else {
                    reject();
                }
            }
        };

        req.send(params.toString());
    });
}
