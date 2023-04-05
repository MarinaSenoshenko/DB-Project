function deleteUser() {
    const req = new XMLHttpRequest();
    const user = document.getElementById('user').value;

    const params = new URLSearchParams({
        user: user,
    });

    req.open('DELETE', '/user?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/users')
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