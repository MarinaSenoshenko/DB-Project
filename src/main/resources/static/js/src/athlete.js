function showNotInCompetition() {
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/athlete/notincompetitionbyperiod/' + start_date + '/' + end_date;
}

function showById() {
    const id = document.getElementById('id').value;

    location.href='/main/athlete/bycompetition/' + id;
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

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addathlete?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/athlete/add/addranking';
}