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
    infofriend=getInfoFriend(eFriend);
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
    infogroup  =getInfoGroup(groupId);
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