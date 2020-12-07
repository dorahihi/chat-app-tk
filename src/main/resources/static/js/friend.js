
// xử lí chấp nhận yêu cầu kết bạn
function acceptRequest(node){

    var friendE= node.getAttribute("id");

    $.ajax({
        url: "/friends/accept",
        type:"POST",
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
// xử lí xóa bạn
function deleletFriend(node){
    let keyGroup = Object.keys(user.friend);
    let emailF = node.getAttribute("id"); // id của danh sách bạn
    emailF = emailF.slice(0,emailF.length-6);
    $.ajax({
        url: "/friends/remove",
        type:"DELETE",
        data:"friendEmail="+emailF+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getInfoGroup(currentGroupID);
            alert("Xóa bạn thành công");
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}