function showSponsorByPeriod() {
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/sponsor/byperiod/' + start_date + '/' + end_date;
}

function addCompany() {
    const req = new XMLHttpRequest();
    const name = document.getElementById('name').value;
    const company = document.getElementById('company').value;

    const params = new URLSearchParams({
        name: name, company: company,
    });

    req.open('POST', '/sponsor?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sponsor';
}

function deleteSponsor() {
    const req = new XMLHttpRequest();
    const sponsor = document.getElementById('sponsor').value;

    const params = new URLSearchParams({
        sponsor: sponsor,
    });

    req.open('DELETE', '/sponsor?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sponsor';
}
