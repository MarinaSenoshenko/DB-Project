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

function addCompetition() {
    const req = new XMLHttpRequest();

    const title = document.getElementById('title').value;
    const period = document.getElementById('period').value;
    const sponsor = document.getElementById('sponsor').value;
    const sport = document.getElementById('sport').value;
    const sportsfacility = document.getElementById('sportsfacility').value;

    const params = new URLSearchParams({
        title: title,
        period : period,
        sponsor: sponsor,
        sport: sport,
        sportsfacility: sportsfacility
    });

    req.open('POST', '/competition?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/competition')
}

function deleteCompetition() {
    const req = new XMLHttpRequest();
    const competition = document.getElementById('competition').value;

    const params = new URLSearchParams({
        competition: competition
    });

    req.open('DELETE', '/competition?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/competition')
}

function addCompetitionPlayer() {
    const req = new XMLHttpRequest();

    const athlete = document.getElementById('athlete').value;
    const competition = document.getElementById('competition').value;
    const wasawarding = document.getElementById('wasawarding').value;
    const result = document.getElementById('result').value;

    const params = new URLSearchParams({
        athlete: athlete,
        competition: competition,
        wasawarding: wasawarding,
        result: result,
    });

    req.open('POST', '/competitionplayer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/competitionplayer')
}

function deleteCompetitionPlayer() {
    const req = new XMLHttpRequest();
    const competition = document.getElementById('competition').value;
    const athlete = document.getElementById('athlete').value;

    const params = new URLSearchParams({
        athlete: athlete, competition: competition
    });

    req.open('DELETE', '/competitionplayer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/competitionplayer')
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
