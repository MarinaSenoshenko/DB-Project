function addRank() {
    const req = new XMLHttpRequest();
    const rank = document.getElementById('rank').value;

    const params = new URLSearchParams({
        rank: rank,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addrank?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/athleterank';
}