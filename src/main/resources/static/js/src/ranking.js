function addRanking() {
    const req = new XMLHttpRequest();
    const sport = document.getElementById('sport').value;
    const rank = document.getElementById('rank').value;

    const params = new URLSearchParams({
        sport: sport,
        rank: rank,
    });

    req.open('POST', '/athlete/addinfo?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athlete');
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
