function showBySport() {
    const sport = document.getElementById('sport').value;
    location.href='/main/trainer/bysport/' + sport;
}

function showByAthleteId() {
    const id = document.getElementById('id').value;
    location.href='/main/trainer/byathlete/' + id;
}


function addTrainer() {
    const req = new XMLHttpRequest();

    const fistName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const patronymic = document.getElementById('patronymic').value;

    const params = new URLSearchParams({
        firstName: fistName,
        patronymic: patronymic,
        lastName: lastName,
    });

    req.open('POST', '/trainer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/trainer')
}

function updateTrainer() {
    const req = new XMLHttpRequest();

    const id = document.getElementById('id').value;
    const fistName = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;
    const patronymic = document.getElementById('patronymic').value;

    const params = new URLSearchParams({
        id: id,
        firstName: fistName,
        patronymic: patronymic,
        lastName: lastName,
    });

    req.open('PUT', '/trainer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/trainer')
}


function deleteTrainer() {
    const req = new XMLHttpRequest();
    const trainer = document.getElementById('trainer').value;

    const params = new URLSearchParams({
        trainer: trainer
    });

    req.open('DELETE', '/trainer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/trainer')
}

function addTraining() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const athlete = document.getElementById('athlete').value;

    const params = new URLSearchParams({
        id: id, athlete: athlete,
    });

    req.open('POST', '/training?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/training')
}

function deleteTraining() {
    const req = new XMLHttpRequest();
    const trainer = document.getElementById('trainer').value;
    const athlete = document.getElementById('athlete').value;

    const params = new URLSearchParams({
        trainer: trainer, athlete: athlete,
    });

    req.open('DELETE', '/training?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/training')
}

function addTrainerLicense() {
    const req = new XMLHttpRequest();
    const trainer = document.getElementById('trainer').value;
    const sport = document.getElementById('sport').value;

    const params = new URLSearchParams({
        trainer: trainer, sport: sport,
    });

    req.open('POST', '/trainerlicense?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/trainerlicense')
}

function deleteTrainerLicense() {
    const req = new XMLHttpRequest();
    const trainerlicense = document.getElementById('trainerlicense').value;

    const params = new URLSearchParams({
        trainerlicense: trainerlicense
    });

    req.open('DELETE', '/trainerlicense?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/trainerlicense')
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