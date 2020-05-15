class User {
    name
    login
    password
    constructor (name, login, password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}

function getAll() {
    const stompClient1 = Stomp.over(new SockJS('/websocket'));
    stompClient1.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient1.subscribe('/topic/message', timeMsg => console.log(timeMsg.body));
    })
}