
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
            getData();
            alert("Xóa bạn thành công");
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}
// xử lí thêm bạn 
selectIdFrmAddFriend.addEventListener("submit",e =>{
    e.preventDefault();
    
    let friendE=$("input[name='friendEmail']").val();
    $.ajax({
        url: url +"/friends/add",
        type:"POST",
        data:   $('#id-add-friend').serialize()+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getData();
            alert("Đã gửi lời mời kết bạn");
            offPlayout('frmAddFriend','display-none',1);
            displayListRequest();
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
});