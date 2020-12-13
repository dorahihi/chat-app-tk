let BoxChat =document.getElementsByClassName('box-chat')[0];

//hàm hiển thị thông tin nhóm 
function insertInfoGroup(){
    let selectIdFromInfoFriendChat = document.getElementById("inforFriend");
    let keyGroup = Object.keys(infogroup.members);
    
    let html =` <div><button onclick="offPlayout('wrapperInfo','display-none',1)">
                    <i class="fas fa-times-circle"></i>
                </button></div>
                <div>Tên nhóm: ${infogroup.groupName}<hr></div>
                <div>Số thành viên : ${keyGroup.length}<hr></div>
                <div>ID nhóm: ${infogroup.groupId}<hr></div>
                <div><button onclick="displayListMember()">Danh sách thành viên</button> <hr></hrr></div>
                <div><button onclick="addMember(${infogroup.groupId+'agroup'})">Thêm thành viên</button><hr></div>
                <div><button onclick="deleteMember(${infogroup.groupId+'agroup'})">Xóa thành viên</button><hr></div>
                <div><button onclick="leaveGroup(${infogroup.groupId+'agroup'})">Rời nhóm</button><hr></div>`;
    if(infogroup.manager===user.email) html +=`<div><button onclick="deleteGroup(${infogroup.groupId+'agroup'})">Xóa nhóm</button></div>`;
    selectIdFromInfoFriendChat.innerHTML= html;
}
function displayListMember(){
    let listMember= document.getElementById('listMember');
    onDisplayPlayout('listMember','display-none',1);
    let html=`<div><button onclick="offPlayout('listMember','display-none',1)">
    <i class="fas fa-times-circle"></i>
</button></div><h4 class="friend">Danh sách thành viên</h4><ul>`;
    let key = Object.keys(infogroup.members);
    for(let i=0;i <key.length;i++){
        html += `<li>${infogroup.members[key[i]]}</li>`
    }
    html+="</ul>"

    listMember.innerHTML =html;
}
// hàm hiển thị thông tin group hoặc friend
function insertInfoFriend(){
    console.log(infofriend);
    let selectIdFromInfoFriendChat = document.getElementById("inforFriend");
    let html =` <div><button onclick="offPlayout('wrapperInfo','display-none',1)">
                    <i class="fas fa-arrow-left"></i>
                </button>
                </div>
                <div>Tên: ${infofriend.userName}<hr></div>
                <div>Email: ${infofriend.email}<hr></div>
                <div>Tuổi: ${infofriend.age}<hr></div>
                <div>Kho lưu trữ:<hr></div>
                <div><button onclick="deleletFriend(${infofriend.email})" >Xóa bạn</button><hr></div>`;
    selectIdFromInfoFriendChat.innerHTML= html;
}
function getTime(t){
      
    // Create a new JavaScript Date object based on the timestamp
    // multiplied by 1000 so that the argument is in milliseconds, not seconds.
    var date = new Date(t);
    // Hours part from the timestamp
    var hours = date.getHours();
    // Minutes part from the timestamp
    var minutes = "0" + date.getMinutes();
    // Seconds part from the timestamp
    var seconds = "0" + date.getSeconds();

    // Will display time in 10:30:23 format
    return hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
}
// chèn tin vào khung
function insertMessage(mess){   
    let status ;
    let type = mess.mesType;
    let time = getTime(mess.timeStamp);
    if (mess.sender !==user.email) status = 1;else status =0;

    if (status===0) { // status = 0 là gửi
        if (type !== "Image"){
            BoxChat.innerHTML += `<div class="stl_mes"><span class="send">${mess.message}<br><span>${time}</span></span></div>`;

        }
        else {
            BoxChat.innerHTML += `<div class="stl_mes"><img class="send img" src="`+mess.message+`"></img></div>`;
        }
    } else {
        if( type !== "Image"){
            BoxChat.innerHTML += `<div class="stl_mes"><span class="receive">${mess.message}<br><span>${time}</span></span></div>`;
        } else{
            BoxChat.innerHTML += `<div class="stl_mes"><img class="receive img" src="`+mess.message+`"></img></div>`;
        }
    }
      //tự động cuộn xuống nội dung mới 
      BoxChat.scrollTop = BoxChat.scrollHeight;   
}
function listMessage(li){
    if(li.length >= 1){
        li.forEach(e=>insertMessage(e));
    }
}
var loadFile = function(event) {

	    var image = document.createElement('img');
        image.src = URL.createObjectURL(event.target.files[0]);
        BoxChat.innerHTML += `<div class="stl_mes"><img class="receive img" src="`+image.src+`"></img></div>`;
};
