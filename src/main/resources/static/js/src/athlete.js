function showNotInCompetition() {
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/athlete/notincompetitionbyperiod/' + start_date + '/' + end_date;
}

function showById() {
    const id = document.getElementById('id').value;
    location.href='/main/athlete/bycompetition/' + id;
}

function showMoreThanOne() {
    location.href='/main/athlete/morethanone/';
}

function showByRankAndSport() {
    const sport = document.getElementById('sport').value;
    const rank = document.getElementById('rank1').value;

    location.href='/main/athlete/bysport/' + sport + '/' + rank;
}

function showByTrainerLicenseAndRank() {
    const trainerlicenseid = document.getElementById('trainerlicenseid').value;
    const athleterank = document.getElementById('rank2').value;

    location.href='/main/athlete/bytrainerlicense/' + trainerlicenseid + '/' + athleterank;
}

function addAthlete() {
    const req = new XMLHttpRequest();

    const club = document.getElementById('club').value;
    const fistName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const patronymic = document.getElementById('patronymic').value;

    const params = new URLSearchParams({
        firstName: fistName,
        patronymic: patronymic,
        lastName: lastName,
        club: club
    });

    req.open('POST', '/athlete?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athlete/')
}

function updateAthlete() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const club = document.getElementById('club').value;
    const fistName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const patronymic = document.getElementById('patronymic').value;

    const params = new URLSearchParams({
        id: id,
        firstName: fistName,
        patronymic: patronymic,
        lastName: lastName,
        club: club
    });

    req.open('PUT', '/athlete?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athlete/')
}

function deleteAthlete() {
    const req = new XMLHttpRequest();
    const athlete = document.getElementById('athlete').value;

    const params = new URLSearchParams({
        athlete: athlete
    });

    req.open('DELETE', '/athlete?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/athlete/')
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
