

/*****************************************************************/ 
const email = user.email;
const sendBtn = document.getElementById('send');
const mes = document.getElementById('message');
const receive = document.getElementById('receive');
const logout = document.getElementById('logout');

// phần chát

let stompClient = null;

console.log("email ne:   +++++: "+email);

const connect = ()  => {

    var socket = new SockJS(url+'/websocket-chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}
connect();


function onConnected() {
    // Subscribe to the Public Topic
  stompClient.subscribe(url+'/user/queue/newMember',  ( data) => data);

 stompClient.subscribe(url+'/topic/newMember', data => console.log("data2: " + data.body));



    // Tell your username to the server
  sendMessage(url+'/app/register', email);

    stompClient.subscribe(urlchat+`/user/${email}/msg`,  data =>{
    console.log(`-------- received message:\n`+ data.body+`\n--------received message!!!!`);
    displayMessage(data);
  });
}

function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function sendMessage(url, message) {
    stompClient.send(url, {}, message);
}


const displayMessage = data =>{
let mess = JSON.parse(data.body);
console.log(" jhdsjfldsk:  "+mess);
receive.innerHTML = `from: `+mess.sender+`\n message: `+ mess.message+"\n to: "+mess.recipient;
}


const onDisconnect = () =>{
  sendMessage(url+'/app/unregister', email);
  stompClient.disconnect();
}

sendBtn.addEventListener('click', () => {
  let messa = mes.value;
  sendMessage(url+'/app/message', JSON.stringify({
    recipient: 'tester',
    sender: email,
    message: messa
  }));
  mes.innerHTML = '';
});

//logout.addEventListener('click', onDisconnect);