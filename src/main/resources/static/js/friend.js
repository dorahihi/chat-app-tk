
// xử lí chấp nhận yêu cầu kết bạn
function acceptRequest(node){

    var friendE= node.getAttribute("id");

    $.ajax({
        url: url +"/friends/accept",
        type:"POST",
        headers:{Authorization:author},
        data:   "friendEmail="+friendE+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getData();
            alert("Đã chấp nhận lời mời kết bạn");
            displayReceiveListRequest();
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}