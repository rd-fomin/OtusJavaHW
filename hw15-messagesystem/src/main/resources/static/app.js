const stompClient1 = Stomp.over(new SockJS('/websocket'));
const stompClient2 = Stomp.over(new SockJS('/websocket'));

function getMessage() {
    stompClient1.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient1.subscribe('/topic/message', timeMsg => document.getElementById('message').textContent = timeMsg.body);
    })
}

function sendMessage() {
    stompClient2.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient2.send('/app/messages', {}, 'First message');
    })
}