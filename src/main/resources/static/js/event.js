const selectFriend = document.getElementsByClassName('friend');
var infogroup;
var infofriend;
var currentGroupID ="" ;
var currentFriendID="";
var currentType;
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
    if (currentFriendID!==""){
        $('#'+currentFriendID+'friend')[0].setAttribute("class",'friend');
    }   
    
    nodeFriend.setAttribute("class","friend active");
    infofriend="";
    currentType="friend";
    let eFriend = nodeFriend.getAttribute('id');
    eFriend =eFriend.slice(0,eFriend.length-6);
  
     
    currentFriendID=eFriend;

    // hàm hiển thị khung chat
    displayFrameChatFriend(eFriend);
    getInfoFriend(eFriend);
}


function groupOnClick(nodeGroup){
    infogroup="";
    currentType="group";
    let groupId = nodeGroup.getAttribute('id');
    groupId =groupId.slice(0,groupId.length-6);
   
    currentGroupID = groupId;
    //đặt toàn bộ lại bình thường
    for(let i= 0;i< selectFriend.length;i++){
        selectFriend[i].setAttribute("class",'friend');
    }
    //set trạng thai fucus
    nodeGroup.setAttribute("class","friend active");
    
    displayFrameChatGroup(groupId);
    getInfoGroup(groupId);
}

function inforOnclick(type){
    // xử lí hiển thị thông tin 
          if(type==="group"){
              insertInfoGroup();
              onDisplayPlayout('wrapperInfo','display-none',0);
          }else{
              insertInfoFriend();
              onDisplayPlayout('wrapperInfo','display-none',0);
          }        
};