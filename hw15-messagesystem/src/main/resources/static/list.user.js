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
    const stompClient = Stomp.over(new SockJS('/websocket'));
    stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', timeMsg => {
            const users = JSON.parse(timeMsg.body);
            console.log(users);
            let html = '';
            users.forEach(function (user, i, array) {
                html += '<tr>';
                html += '<td>' + user.id + '</td>';
                html += '<td>' + user.name + '</td>';
                html += '<td>' + user.login + '</td>';
                html += '<td>' + user.password + '</td>';
                html += '</tr>';
            });
            document.querySelector('#users').innerHTML = html;
        });
    })
}