
var url = "https://chatapp-kkt.herokuapp.com";
var user ={
    "id":"5f9eab9ce56cb70d5191b318",
    "email":"tester","password":"",
    "age":"25",
    "gender":"FEMALE",
    "userName":"tester",
    "role":"ROLE_USER",
    "friend":{"tester1":"tester1","khang":"Hoàng Khang"},
    "group":{
        "nhommoi26763368":"Nhóm mới",
        "nhoma26763370":"nhóm a",
        "nhom5ne26768371":"nhóm 5 nè",
        "nhom7ne26768861":"Nhóm 7 nè",
        "nhom12326769210":"Nhóm 123",
        "nhom3ne26766055":"Nhóm 3 nè ",
        "nhom4ne26766055":"Nhóm 4 nè "},
    "friendRequest":{
        "thanh":"Tín",
        "le":"Lợi",
        "Ming":"Minh"
    },
    "receivedFriendRequest":{
        "tanh":"Tiến",
        "lien":"Thi",
        "Ming":"Mẫn"
    }};
var author;
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

/************************************************************************** */

getCookie();

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
