$(document).ready(function(){
    const username = "userName";
    $("#disconn").on("click", (e) => {
        disconnect();
    })
    $("#button-send").on("click", (e) => {
        send();
    });
    const websocket = new WebSocket("ws://localhost:8080/ws/chat");
    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;
    function send(){
        let msg = document.getElementById("msg");
        console.log(username + ":" + msg.value);
        websocket.send(username + ":" + msg.value);
        msg.value = '';
    }
    function onClose(evt) {
        var str = username + ": 님이 방을 나가셨습니다.";
        websocket.send(str);
    }
    function onOpen(evt) {
        var str = username + ": 님이 입장하셨습니다.";
        websocket.send(str);
    }
    function onMessage(msg) {
        var data = msg.data;
        var sessionId = null;
        var message = null;
        var arr = data.split(":");
        for(var i=0; i<arr.length; i++){
            console.log('arr[' + i + ']: ' + arr[i]);
        }
        var cur_session = username;
        console.log("cur_session : " + cur_session);
        sessionId = arr[0];
        message = arr[1];
        console.log("sessionID : " + sessionId);
        console.log("cur_session : " + cur_session);
        if(sessionId == cur_session) {
            var str = "<div class='col-6'>";
            str += "<div class='alert alert-secondary'>";
            str += "<b>" + sessionId + " : " + message + "</b>";
            str += "</div></div>";
            $("#msgArea").append(str);
        }
        else {
            var str = "<div class='col-6'>";
            str += "<div class='alert alert-warning'>";
            str += "<b>" + sessionId + " : " + message + "</b>";
            str += "</div></div>";
            $("#msgArea").append(str);
        }
    }
})