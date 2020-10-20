var coords = [];
var coords2 = [];
var actionBool = false;

coords.push(document.getElementById("ship1_id").innerHTML);
coords2.push(document.getElementById("ship2_id").innerHTML);
var gameID = document.getElementById("game_id").innerHTML;

$(document).ready(function () {
    $(".hex span").on('click', function(){
        var tplayer = $("#turnplayer");

        if (actionBool) {
            $(this).css({backgroundColor: 'red'});
            actionBool = false;
        }
        else {
            if (tplayer.text() === "ship1") {
                var p1movesleft = $("#p1moves").text();
                var p1count = Number(p1movesleft);
                if (p1count > 0) {
                    $(this).css({backgroundColor: 'yellow'});
                    if (p1movesleft !== "0") {
                        $("#p1moves").text(p1movesleft - 1);
                    }
                }
            }
            else if (tplayer.text() === "ship2") {
                var p2movesleft = $("#p2moves").text();
                var p2count = Number(p2movesleft)
                if (p2count > 0) {
                    $(this).css({backgroundColor: 'green'});
                    if (p2movesleft !== "0") {
                        $("#p2moves").text(p2movesleft - 1);
                    }
                }
            }
        }
    });
});

function sendCoord(posx, posy) {
    var tplay = $("#turnplayer").text();
    if (tplay === "ship1") {
        var p1moves = $("#p1moves");
        if (p1moves.text() === "0" && !actionBool) {
            alert("You have no more moves left")
            return;
        }
        coords.push(posx);
        coords.push(posy);
    }
    else if (tplay === "ship2") {
        var p2moves = $("#p2moves");
        if (p2moves.text() === "0" && !actionBool) {
            alert("You have no more moves left")
            return;
        }
        coords2.push(posx);
        coords2.push(posy);
    }
    //alert(posx + ", " + posy);
}

function send() {
    var tplayer = $("#turnplayer");

    if (tplayer.text() === "ship1") {
        coords.push(actionBool);
        coords.push(gameID);
        tplayer.text("ship2");

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/movement", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(coords);
    }
    else if (tplayer.text() === "ship2"){
        coords2.push(actionBool);
        coords2.push(gameID);
        tplayer.text("ship1");

        var xhttp2 = new XMLHttpRequest();
        xhttp2.open("POST", "/movement", true);
        xhttp2.setRequestHeader("Content-type", "application/json");
        xhttp2.send(coords2);
    }
    $(".hex span").css({backgroundColor: 'darkturquoise'})
}

function end() {
    location.reload();
}

function move() {
    actionBool = false;
}

function attack() {
    actionBool = true;
    var tplayer = $("#turnplayer");
    if (tplayer.text() === "ship1") {
        coords.push(actionBool);
    }
    else if (tplayer.text() === "ship2"){
        coords2.push(actionBool);
    }
}

/*
/!*<![CDATA[*!/
    coords.push(/!*[[${ship1.getShip_id()}]]*!/);
    coords2.push(/!*[[${ship2.getShip_id()}]]*!/);
    var gameID = /!*[[${gameid}]]*!/
/!*]]>*!
/*/
