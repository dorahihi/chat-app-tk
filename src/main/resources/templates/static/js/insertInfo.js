let BoxChat =document.getElementsByClassName('box-chat');

//hàm hiển thị thông tin nhóm 
function insertInfoGroup(){
    console.log(infogroup);
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
// chèn tin vào khung
function insertMessage(mes,status,name){   
    
    if (status===0) { // status = 0 là gửi
        BoxChat[0].innerHTML += `<div class="stl_mes"><span class="send">`+ mes+`</span></div>`;
    } else {
        BoxChat[0].innerHTML += `<div class="stl_mes"><span class="receive">`+ mes+`</span></div>`;
    }
      //tự động cuộn xuống nội dung mới 
      BoxChat[0].scrollTop = BoxChat[0].scrollHeight; 
}
var loadFile = function(event) {

	    var image = document.createElement('img');
        image.src = URL.createObjectURL(event.target.files[0]);
        BoxChat[0].innerHTML += `<div class="stl_mes"><img class="receive img" src="`+image.src+`"></img></div>`;
};
