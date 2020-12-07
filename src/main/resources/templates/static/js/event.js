const selectFriend = document.getElementsByClassName('friend');
var infogroup;
var infofriend;
var currentGroupID ="" ;
// đặt tên và trang thái cho web selectStatus.innerText="Đang offline"
window.addEventListener('online',setStatus);
window.addEventListener('offline',setStatus);
function setStatus(event){
    if(navigator.onLine){
        console.log("online");
    }else{
        console.log("offline");
    }
}
/****************************************************************** */

// đăt lại trạng thái fucus
function friendOnClick(nodeFriend){
    infofriend="";
    let friend = selectFriend;
    for(let i= 0;i< friend.length;i++){
        friend[i].setAttribute("class",'friend');
    }
    nodeFriend.setAttribute("class","friend active");
    let eFriend = nodeFriend.getAttribute('id');
     eFriend =eFriend.slice(0,eFriend.length-6);
    // hàm hiển thị khung chat
    displayFrameChatFriend(eFriend);
    infofriend={"id":"5f9bdb249f67c803a9643da4","email":"khang","password":"","age":"30","gender":"MALE","userName":"Nguyen Khang","role":"ROLE_USER","friend":{"tester":"tester","tester1":"tester1"},"group":{"nhom3ne26766055":"Nhóm 3 nè ","nhom4ne26766055":"Nhóm 4 nè ","nhom2ne26766056":"Nhóm 2 nè ","nhom5ne26768371":"nhóm 5 nè","nhom6ne26768376":"nhóm 6 nè","nhom7ne26768861":"Nhóm 7 nè","nhom9ne26768881":"nhóm 9 nè","nhoma26763370":"nhóm a"},"friendRequest":{},"receivedFriendRequest":{}};
}


function groupOnClick(nodeGroup){
    infogroup="";
    let friend = selectFriend;
    //đặt toàn bộ lại bình thường
    for(let i= 0;i< friend.length;i++){
        friend[i].setAttribute("class",'friend');
    }
    //set trạng thai fucus
    nodeGroup.setAttribute("class","friend active");
    let groupId = nodeGroup.getAttribute('id');
    groupId =groupId.slice(0,groupId.length-6);
    displayFrameChatGroup(groupId);
    //infogroup  =getInfoGroup(idchat);
    infogroup = {
        "id":"5fb69877e1be3115cb80b663",
        "groupId":"nhommoi26763368",
        "members":{"tester":"tester","khang":"Hoàng Khang"},
        "manager":"tester",
        "groupName":"Nhóm mới"};
}

function inforOnclick(idchat,type){
    idchat = idchat.getAttribute('id');
    idchat =idchat.slice(0,idchat.length-6);
    // xử lí hiển thị thông tin 
          if(type==="group"){
              insertInfoGroup();
              onDisplayPlayout('wrapperInfo','display-none',0);
          }else{
              insertInfoFriend();
              onDisplayPlayout('wrapperInfo','display-none',0);
          }        
};