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

    req.open('POST', '/sportsfacility/arena?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/arena');
}

function updateArena() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;
    const id = document.getElementById('id').value;

    const params = new URLSearchParams({
        id: id,
        param: param,
    });

    req.open('PUT', '/sportsfacility/arena?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/arena');
}

function deleteArena() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('DELETE', '/sportsfacility/arena?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/arena');
}

function addCourt() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('POST', '/sportsfacility/court?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/court');
}

function updateCourt() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        id: id,
        param: param,
    });

    req.open('PUT', '/sportsfacility/court?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/court');
}

function deleteCourt() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('DELETE', '/sportsfacility/court?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/court');
}

function addGym() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('POST', '/sportsfacility/gym?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/gym');
}

function updateGym() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        id: id, param: param,
    });

    req.open('PUT', '/sportsfacility/gym?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/gym');
}

function deleteGym() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('DELETE', '/sportsfacility/gym?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/gym');
}

function addFacility() {
    const req = new XMLHttpRequest();
    const address = document.getElementById('address').value;
    const type = document.getElementById('type').value;

    const params = new URLSearchParams({
        address : address,
        type : type,
    });

    req.open('POST', '/sportsfacility?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/add/add_' + type);
}

function updateFacility() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const address = document.getElementById('address').value;
    const type = document.getElementById('type').value;

    const params = new URLSearchParams({
        id: id, address : address, type : type,
    });

    req.open('PUT', '/sportsfacility?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/');
}

function deleteFacility() {
    const req = new XMLHttpRequest();
    const sportsfacility = document.getElementById('sportsfacility').value;

    const params = new URLSearchParams({
        sportsfacility : sportsfacility
    });

    req.open('DELETE', '/sportsfacility?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/');
}

function addStadium() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('POST', '/sportsfacility/stadium?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/stadium');
}

function updateStadium() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        id: id, param: param,
    });

    req.open('PUT', '/sportsfacility/stadium?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/sportsfacility/stadium');
}

function deleteStadium() {
    const req = new XMLHttpRequest();
    const param = document.getElementById('param').value;

    const params = new URLSearchParams({
        param: param,
    });

    req.open('DELETE', '/sportsfacility/stadium?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    req.send(params.toString());

    changeState(req, params, '/main/sportsfacility/stadium');
}

function addCourtSurface() {
    const req = new XMLHttpRequest();
    const surface = document.getElementById('surface').value;

    const params = new URLSearchParams({
        surface : surface,
    });

    req.open('POST', '/courtsurface?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/courtsurface');
}

function updateCourtSurface() {
    const req = new XMLHttpRequest();
    const id = document.getElementById('id').value;
    const surface = document.getElementById('surface').value;

    const params = new URLSearchParams({
        id: id, surface : surface,
    });

    req.open('PUT', '/courtsurface?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/courtsurface');
}

function deleteCourtSurface() {
    const req = new XMLHttpRequest();
    const surface = document.getElementById('surface').value;

    const params = new URLSearchParams({
        surface : surface,
    });

    req.open('DELETE', '/courtsurface?' + params.toString());
    req.setRequestHeader("Content-Type", "application/json");
    changeState(req, params, '/main/courtsurface');
}

function changeState(req, params, ref) {
    new Promise((resolve, reject) => {
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
