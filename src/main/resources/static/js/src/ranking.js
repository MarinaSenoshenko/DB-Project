function addRanking() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const sport = document.getElementById('sport').value;
    const rank = document.getElementById('rank').value;

    const params = new URLSearchParams({
        id: id,
        sport: sport,
        rank: rank,
    });

    req.open('POST', '/athleteranking?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athleteranking');
}

function deleteRanking() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;
    const athlete = document.getElementById('athlete').value;

    const params = new URLSearchParams({
        sport: sport,
        athlete: athlete,
    });

    req.open('DELETE', '/athleteranking?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athleteranking');
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
