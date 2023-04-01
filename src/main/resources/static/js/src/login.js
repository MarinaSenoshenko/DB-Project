function add() {
    const req = new XMLHttpRequest();

    const login = document.getElementById('login').value;
    const fistName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const patronymic = document.getElementById('patronymic').value;
    const password = document.getElementById('password').value;

    const params = new URLSearchParams({
        login: login,
        firstName: fistName,
        patronymic: patronymic,
        lastName: lastName,
        password: password
    });

    req.open('POST', '/athletepassword?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main')
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
