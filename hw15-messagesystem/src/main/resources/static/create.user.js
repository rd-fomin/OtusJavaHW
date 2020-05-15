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

function sendMessage() {
    const stompClient = Stomp.over(new SockJS('/websocket'));

    const name = document.querySelector('#name-input').value;
    const login = document.querySelector('#login-input').value;
    const password = document.querySelector('#password-input').value;

    let user = new User(name, login, password);
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient.send('/app/messages/create', {}, JSON.stringify(user));
    })
}