
function getData(email){
    $.ajax({
        url:url+email,
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
            url:url+"/groups/profile/"+idGroup,
            type:"GET",
            async:false,
            headers:{Authorization:author},
            dataType:"text",
            success: function(res) {
                groupInfo = JSON.parse(res);
            },
             error: () =>{
                alert("Incorrect!");
             }
        });
    return groupInfo;
}

// lấy thông tin bạn chat
function getInfoFriend(emailF){
    let friendInfo;
        $.ajax({
            url:url+"/users/"+emailF,
            type:"GET",
            async:false,
            headers:{Authorization:author},
            dataType:"text",
            success: function(res) {
                friendInfo = JSON.parse(res);
            },
             error: () =>{
                alert("Incorrect!");
             }
        });
    return friendInfo;
}