function showBySport() {
    const sport = document.getElementById('sport').value;

    location.href='/main/trainer/bysport/' + sport;
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

    req.open('POST', '/addtrainer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/trainer';
}

function deleteTrainer() {
    const req = new XMLHttpRequest();
    const trainer = document.getElementById('trainer').value;

    const params = new URLSearchParams({
        trainer: trainer
    });

    req.open('DELETE', '/addtrainer?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/trainer';
}
