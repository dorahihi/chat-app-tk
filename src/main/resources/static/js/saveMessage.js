  // lưu vào stogege
function saveMessage(mess){

    let  li= sessionStorage[mess.chatId];
    if (li ===undefined)  li = [];
    else li = JSON.parse(li);
    li.push(mess);
    sessionStorage[mess.chatId]= JSON.stringify(li);
}