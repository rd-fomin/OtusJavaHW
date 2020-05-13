let stompClient = Stomp.over(new SockJS('/websocket'))
function getMessage() {
    stompClient.connect({}, frame => {
        console.log('Connected: ' + frame)
        stompClient.subscribe('/topic/message', timeMsg => document.getElementById('message').innerHTML = timeMsg.body)
    })
}

function sendMessage() {
    stompClient.connect({}, () => {
        stompClient.send('/app/message', {}, 1)
    })
}