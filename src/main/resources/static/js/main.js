
var url = "https://chat-app-tk.herokuapp.com";

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);  
    if (parts.length === 2) return parts.pop().split(';').shift();
}
var email = getCookie('email');
console.log(email);
var user;
getData(email);
var listChatting={};
// const selectBoxInfoUser =document.getElementsByClassName("box-info-user");
// const selectChatBox = document.getElementsByClassName("chatBox");
// const selectTitleBox=document.getElementsByClassName("title-box");
// const selectBoxChat =document.getElementsByClassName('box-chat');
// const selectFormUpdate=document.getElementsByClassName("fromUpdate");
// const selectFriend = document.getElementsByClassName('friend');
// const selectOverlay =document.getElementsByClassName('overlay');
// const selectIdFrmAddGroup = document.getElementById('id-add-group');
// const selectIdFrmUpdateInfo = document.getElementById('id-frm-updata-info');
// const selectIdSearchFriend = document.getElementById('id-search-friend');
// const selectIdFrmAddFriend = document.getElementById("id-add-friend");
// const selectNameUser = document.getElementById("nameUser");
// const selectStatus = document.getElementById("status");
// const selectListFriend = document.getElementsByClassName("list-friend");


/**********************************************************************/
// đợi tài liệu load và hiển thị danh sách bạn 
function scriptDisplay(){
    onDisplayListChat(); 
    onDisplayPlayout('btnAddGroup','display-none',0);
}




/*****************************************************************/


/*******************************************************************/

/************************************/
// xử lí logout 
function onLogout(){
    window.open(url,"_self");
}
