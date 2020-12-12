

/*****************************************************************/ 
const sendBtn = document.getElementById('send');
const mes = document.getElementById('message');
const receive = document.getElementById('receive');
const logout = document.getElementById('logout');
//let url ='https://secret-brook-88276.herokuapp.com/';
// phần chát

let stompClient = null;

console.log("email ne:   +++++: " + email);

const connect = ()  => {

    var socket = new SockJS('/websocket-chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}
connect();


function onConnected() {
    // Subscribe to the Public Topic
  stompClient.subscribe('/user/queue/newMember',  ( data) => data);

 stompClient.subscribe('/topic/newMember', data => console.log("data2: " + data.body));



    // Tell your username to the server
  sendMessage('/app/register', email);

    stompClient.subscribe(`/user/${email}/msg`,  data =>{
    console.log(`-------- received message:\n`+ data.body+`\n--------received message!!!!`);
    displayMessage(data);
   
  });
}

function onError(error) {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function sendMessage(url, message) {
    stompClient.send(url, {}, message);
    
}


const displayMessage = data =>{
  let mess = JSON.parse(data.body);
  console.log(" jhdsjfldsk:  "+ mess);
  //receive.innerHTML = `from: `+mess.sender+`\n message: `+ mess.message+"\n to: "+mess.recipient;
   if (currentFriendID !=="" && (currentFriendID === mess.sender || chatting===mess.sender)) 
   insertMessage(mess);
   saveMessage(mess);
}


const onDisconnect = () =>{
  sendMessage('/app/unregister', email);
  stompClient.disconnect();
}

sendBtn.addEventListener('click', () => {
  let messa = mes.value;
  if(messa!==''){
    let tin = aMessage(chatting, email, messa,'Text');
    sendMessage('/app/message',tin);
    mes.value = '';
    messa= JSON.parse(tin);
    insertMessage(messa);
    saveMessage(messa);
  }
  
});
function getChatId(recipient,sender){
  let chatId={};

  if (currentType==="friend"){
    if (sender > recipient) chatId.id = sender+recipient;
    else chatId.id = recipient+sender;
    chatId.type = "PrivateMessage";
  }else{
    chatId.id = chatting;
    chatId.type = "GroupMessage"
  }
  return chatId;
}
function aMessage(recipient,sender,message,messageType){
  let chatId = getChatId(recipient,sender);
  
  return JSON.stringify({
    recipient: recipient,
    sender: sender,
    message: message,
    chatId:chatId.id,
    type:chatId.type,
    messageType:messageType
  })
}
mes.addEventListener('keyup',e=>{    
  
  let messa= mes.value;
  
  if(e.keyCode =="13" &&  messa!=""){
    let tin =  aMessage(chatting, email,messa,'Text');
    sendMessage('/app/message',tin);
    mes.value = '';
    messa = JSON.parse(tin);
    insertMessage(messa);
    saveMessage(messa);
  }
});
function getMessage(chatId){
  let data;
  $.ajax({
      url: "/users/messages",
      type:"GET",
      async:false,
      headers:   {chatId:chatId,email:user.email},
      dataType:"text",
      success: function(res) {
         // console.log("tin nhắn đã gửi:",res);
          data = res;
      },
      error: () =>{
          alert("Lỗi rồi!");
      }
  });
  return data;
}
function getMessageGroup(groupId){
  let data;
  $.ajax({
      url: "/groups/messages",
      type:"GET",
      async:false,
      headers:   {groupId:groupId,email:user.email},
      success: function(res) {
          console.log("tin nhắn nhóm :",res);
          data = res;
      },
      error: () =>{
          alert("Lỗi rồi!");
      }
  });
  return data;
}