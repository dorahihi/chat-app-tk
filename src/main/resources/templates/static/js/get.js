
function getCookie() {
    $.ajax({
       type: "POST",
       url: url+"/auth",
       async: true,
       data: "email=tester&password=tester",
       xhrFields:{
            withCredentials:true
       },
       dataType:"text",
       success: function(output,status,res) {
        author =res.getResponseHeader("Authorization");
        alert("thanh ",author);
        getData();

       }, 
       error: () =>{
          alert("Incorrect email or password!");
       }
    });
}
function getData(){
    $.ajax({
        url:url+"/users/tester",
        type:"GET",
        async:true,
        headers:{Authorization:author},
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