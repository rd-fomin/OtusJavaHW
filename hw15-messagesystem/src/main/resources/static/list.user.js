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
        stompClient.send('/app/messages/list', {}, 'getAll');
        stompClient.subscribe('/topic/message', timeMsg => {
            if (timeMsg.body === 'createUser') {
                stompClient.send('/app/messages/list', {}, 'getAll');
            } else {
                const users = JSON.parse(timeMsg.body);
                let html = '';
                users.forEach(user => {
                    html += '<tr>';
                    html += `<td>${user.id}</td>`;
                    html += `<td>${user.name}</td>`;
                    html += `<td>${user.login}</td>`;
                    html += `<td>${user.password}</td>`;
                    html += '</tr>';
                });
                document.querySelector('#tbody_users').innerHTML = html;
            }
        });
    })
}