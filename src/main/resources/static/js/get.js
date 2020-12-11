

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);  
    if (parts.length === 2) return parts.pop().split(';').shift();
}

var email = getCookie('email');
var user;
function getData(){
    $.ajax({
        url:"/users/"+email,
        type:"GET",
        async:true,
        dataType:"text",
        success: function(res) {
            user = JSON.parse(res);
            console.log(user);
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}
//lấy thông tin nhóm chat
function getInfoGroup(idGroup){
    let groupInfo;
        $.ajax({
            url:"/groups/profile/"+idGroup,
            type:"GET",
            async:false,
            dataType:"text",
            success: function(res) {
                groupInfo = JSON.parse(res);
                infogroup = groupInfo;
            },
             error: () =>{
                alert("Incorrect!");
             }
        });
}

// lấy thông tin bạn chat
function getInfoFriend(emailF){
    let friendInfo;
        $.ajax({
            url:"/users/"+emailF,
            type:"GET",
            async:false,
            dataType:"text",
            success: function(res) {
                friendInfo = JSON.parse(res);
                infofriend = friendInfo;
            },
             error: () =>{
                alert("Incorrect!");
             }
        });
}