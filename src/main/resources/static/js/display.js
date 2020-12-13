const selectBoxInfoUser =document.getElementsByClassName("box-info-user")[0];
const selectChatBox = document.getElementById("khungchat");
const selectTitleBox=document.getElementsByClassName("title-box")[0];
const selectBoxChat =document.getElementsByClassName('box-chat')[0];
const selectFormUpdate=document.getElementsByClassName("fromUpdate")[0];
const selectOverlay =document.getElementsByClassName('overlay')[0];
const selectListFriend = document.getElementsByClassName("list-friend")[0];
const selectNameUser = document.getElementById("nameUser");
var chatting;
var listChatting={};

// hiển thị khung chat cho group
function displayFrameChatGroup(groupId){
    //tìm xem có tồn tại trong danh sách chat chưa
    let haveExist =listChatting[groupId];
    
    // Nếu chưa thì tạo mới
    if (haveExist ===undefined){
        listChatting[groupId]={ 
            type:"group",
            titleChat:user.group[groupId]
        };
    };

    // đặt id cho khung chat
    groupId +="aframe";
    displayFrameChat(groupId);
}

// hiển thị khung chat 
function displayFrameChatFriend(eFriend){
    // search danh sach chat co được tạo chưa
    let haveExist =listChatting[eFriend];
    // Nếu chưa thì tạo mới
    if (haveExist ===undefined){
        listChatting[eFriend]={
            type:"private",
            titleChat:user.friend[eFriend],
        };
    };
    // đặt id cho khung chat
    eFriend +="aframe";
    displayFrameChat(eFriend);
}
function displayListChatting(nodeId){
    nodeId = nodeId.getAttribute('id');
    displayFrameChat(nodeId);
}
function displayFrameChat(emailchatting){
    let idchat = emailchatting.slice(0,emailchatting.length-6);
    chatting= idchat;
     //hiển thị khung chat
    selectChatBox.setAttribute("class","chatBox display");
     //dat lai hien thi khung chat
    selectBoxChat.innerText="";
    // đăt title tin nhắn      
    selectTitleBox.innerText= listChatting[idchat].titleChat;
    //let type =listChatting[idchat].type;
     // điền tin nhắn
    // listChatting[idchat].message.forEach(x=>{
    //     insertMessage(x.content,x.status);
    // });
     //tắt nút thêm nhóm
    offPlayout('btnAddGroup','display-none');
    let btnDisplayInfor = document.getElementById('btnDisplayInfo');

    if(listChatting[idchat].type==="group"){
        btnDisplayInfor.setAttribute('onclick',`inforOnclick('group')`)
    }else{
        btnDisplayInfor.setAttribute('onclick',`inforOnclick('private')`)
    }
    let listM =[];
    if (currentType=="friend")   {
        let chatId= getChatId(currentFriendID,user.email);
        listM=  sessionStorage[chatId];
        if(listM !== undefined){
            listM=JSON.parse(listM);
            listMessage(listM);
        } 
        else{
            getMessage(chatId.id);
        } 
    }else{
        listM = sessionStorage[chatting];
        if (listM != undefined){
            listM = JSON.parse(listM);
            listMessage(listM);
        }else{
             getMessageGroup(chatting);        
        }
    }

}

//hàm hiển thị danh sách bạn
function boxMenu(ds){

    selectListFriend.style.display="grid";
    selectBoxInfoUser.style.display ="none";
    selectListFriend.innerHTML =ds; 
}
function displayListFriend(){
    let keyFriend = Object.keys(user.friend);
    
    // danh sách bạn
    let listHTML='';
    keyFriend.forEach(e=>{
        idNew = e+'friend'
        listHTML += `<li class='friend' id="${idNew}">
        <div class='picture'></div>
        <div class='nameFriend' onclick="friendOnClick(${idNew})" >${user.friend[e]}</div>
        <button class="cancel" onclick="deleletFriend(${idNew})">
        <i class="far fa-times-circle"></i>
        </button>
        </li>`;
    });
    boxMenu(listHTML);   
    onDisplayPlayout('btnAddGroup','display-none',0);

}

// hiển thị danh sách bạn đang chat 
function onDisplayListChat(){
    selectNameUser.innerText = user.userName;

    let idchat = Object.keys(listChatting);
    let html="";
    idchat.forEach(x =>{
        html+=`<li class='friend' id="${x+'alistC'}" onclick="displayListChatting(this)">
        <div class='picture' ></div>
        <div class='nameFriend'>${listChatting[x].titleChat}</div>
        </li>` ;
    });
    onDisplayPlayout('btnAddGroup','display-none',0);

    boxMenu(html);   
}
// hiển thị danh sách nhóm đã join

function displayListGroup(){
    let listHTML=''; 
    let keyGroup = Object.keys(user.group);

    keyGroup.forEach(e=>{
        idNew = e+'agroup'
        listHTML += `<li class='friend' id="${idNew}"  onclick="groupOnClick(${idNew})" >
        <div class='picture'></div>
        <div class='nameFriend'>${user.group[e]}</div>
        </li>`;
    });
    boxMenu(listHTML);
    //bật nút thêm bạn
}

//hiển thị danh sách những yêu cầu kết bạn
function displayReceiveListRequest(){
    //create list request
    let keyRe = Object.keys(user.receivedFriendRequest);
    let listHTML="";
    if(keyRe.length===0){
        listHTML +=`<h6 class="friend">Lời mời kết bạn: (rỗng)</h6>`;;
    }else{
        listHTML =`<h6 class="friend">Lời mời kết bạn</h6>`;
        keyRe.forEach(function(e,i){
            listHTML+=`<li class='friend'>
            <div class='picture'></div>
            <div class='nameFriend'>${user.receivedFriendRequest[e]}</div>
            <button class="accept" onclick="acceptRequest(this)" id="${e}">
                <i class="far fa-check-circle"></i>
            </button >
            <button class="cancel">
                <i class="far fa-times-circle"></i>
            </button>
            </li>`});
    }
    onDisplayPlayout('btnAddGroup','display-none',0);
    keyRe = Object.keys(user.friendRequest);
    listHTML += `<h6 class="friend">Yêu cầu đã gửi</h6>`; 
        keyRe.forEach(function(e,i){
            listHTML+=`<li class='friend'>
            <div class='picture'></div>
            <div class='nameFriend'>${user.friendRequest[e]}</div>
            <button class="cancel" id="${e}">
                <i class="far fa-times-circle"></i>
            </button>
            </li>`});
    boxMenu(listHTML);  
}

// hàm đóng khung chat lại
function offDislayChat(){
    selectChatBox.setAttribute("class","chatBox");
    selectTitleBox.innerText="Chọn bạn để chat";
    selectBoxChat.innerHTML ="";
    chatting= "";
    infofriend="";
    infofriend="";
    $('#'+currentFriendID+'friend').setAttribute("class",'friend');
    currentFriendID="";
    currentType="";
    currentGroupID="";
}

//Hiển thị form cập nhật
function onDisplayFormUpdate(){
    selectFormUpdate.style.display="block";
    //set thông tin
    
    document.getElementsByName('userName')[0].setAttribute("placeholder",user.userName);

    document.getElementsByName('age')[0].setAttribute("placeholder",user.age);
    selectOverlay.style.display='block';

}

//hàm hiển thị playout ẩn
function onDisplayPlayout(idPlayout,classDel,zIndex){
    let attributeClass = document.getElementById(idPlayout).getAttribute("class");
    var attributeClassNew =  attributeClass.replace(classDel,"");
    if (attributeClass===attributeClassNew && idPlayout !=='btnAddGroup') attributeClassNew +=classDel;
    document.getElementById(idPlayout).setAttribute("class",attributeClassNew);
    if(zIndex===1)selectOverlay.style.display='block';
}

// tắt playout
function offPlayout(idPlayout,classInsert){

    let attributeClass = document.getElementById(idPlayout).getAttribute("class"); 
    // tìm không thấy thì + class display-none
    if(attributeClass.search(classInsert)==(-1))
    document.getElementById(idPlayout).setAttribute("class",attributeClass+classInsert); 
    
    selectOverlay.style.display='none';
}

function offDisplayFormUpdate(){

    document.getElementsByClassName("fromUpdate")[0].style.display="none";
    selectOverlay.style.display='none';
}