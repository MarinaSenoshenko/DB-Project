function showByTrackNumber() {
    const trackNumber = document.getElementById('trackNumber').value;
    location.href='/main/sportsfacility/arena/' + trackNumber;
}

function showBySurface() {
    const surface = document.getElementById('surface').value;
    location.href='/main/sportsfacility/court/' + surface;
}

function showByFloor() {
    const floor = document.getElementById('floor').value;
    location.href='/main/sportsfacility/gym/' + floor;
}

function showByCapacity() {
    const capacity = document.getElementById('capacity').value;
    location.href='/main/sportsfacility/stadium/' + capacity;
}

function showByPeriod() {
    const start_date = document.getElementById('start_date').value;
    const end_date = document.getElementById('end_date').value;

    location.href='/main/sportsfacility/bycompetitionperiod/' + start_date + '/' + end_date;
}


function addArena() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addsportsfacility/addarena?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportsfacility/arena';
}

function addCourt() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addsportsfacility/addcourt?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportsfacility/court';
}

function addGym() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addsportsfacility/addgym?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportsfacility/gym';
}

function addFacility() {
    const req = new XMLHttpRequest();
    const address = document.getElementById('address').value;
    const type = document.getElementById('type').value;

    const params = new URLSearchParams({
        address : address,
        type : type,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addsportsfacility?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportsfacility/add/add_' + type;
}

function addStadium() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addsportsfacility/addstadium?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/sportsfacility/stadium';
}

function addCourtSurface() {
    const req = new XMLHttpRequest();
    const surface = document.getElementById('surface').value;

    const params = new URLSearchParams({
        surface : surface,
    });

    req.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //some visual changes here
        }
    }

    req.open('POST', '/addcourtsurface?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    location.href='/main/courtsurface';
}