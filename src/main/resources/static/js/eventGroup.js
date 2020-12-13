const selectIdFrmAddFriend = document.getElementById("id-add-friend");
const selectIdFrmAddGroup = document.getElementById('id-add-group');

// thêm member 
function addMember(groupId){
    console.log(groupId);
    groupId = groupId.getAttribute('id');
    groupId =groupId.slice(0,groupId.length-6);

    onDisplayPlayout("idAddMember","display-none",1);
}

let selectFrmAddMember = document.getElementById("frmAddMember");
selectFrmAddMember.addEventListener("submit",e=>{
        e.preventDefault();
        $.ajax({
            url: "/groups/add",
            type:"POST",
            data:   $('#frmAddMember').serialize()+"&groupId="+currentGroupID+"&email="+user.email,
            dataType:"text",
            success: function(res) {
                getInfoGroup(currentGroupID);
                if(res=="SUCCESS")alert("Đã thêm bạn thành công")
                else alert ("Người dùng không tồn tại hoặc đã là thành viên");
                offPlayout('idAddMember','display-none',1);
            },
             error: () =>{
                alert("Incorrect!");
             }
        });
});

/******************************************************************************/
// xử lí sự kiện tạo nhóm
selectIdFrmAddGroup.addEventListener("submit",e =>{
    e.preventDefault();
    console.log(e.submitter);
    // sử lí tạo nhóm
    var a =  $('#id-add-group').serialize()  + "&email="+user.email;
    console.log(a);
    $.ajax({
        url: "/groups/create",
        type:"POST",
        data: a,
        dataType:"text",
        success: function(res) {
            getData(displayListGroup);
            alert("Tạo nhóm thành công");
            offPlayout('frmAddGroup','display-none');
            displayListGroup();
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
});

// xóa nhóm 
function deleteGroup(idnode){
    let groupId = idnode.getAttribute("id"); // id của danh sách bạn
    groupId =groupId.slice(0,groupId.length-6);
    $.ajax({
        url: "/groups/delete",
        type:"DELETE",
        data:"groupId="+groupId+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getData(displayListGroup);
            alert("Xóa nhóm thành công");
            displayListGroup();
            offPlayout('frmAddGroup','display-none');
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
            getData(displayListFriend);
            alert("Đã gửi lời mời kết bạn");
            offPlayout('frmAddFriend','display-none',1);
            displayListRequest();
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
});

// xử lí xóa bạn
function deleletFriend(node){
    let keyGroup = Object.keys(user.friend);
    let emailF = node.getAttribute("id"); // id của danh sách bạn
    $.ajax({
        url: "/friends/remove",
        type:"DELETE",
        data:"friendEmail="+emailF+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getData(displayListFriend);
            alert("Xóa bạn thành công");
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}
// xử lí rời nhóm
function leaveGroup(){
    $.ajax({
        url: "/groups/leave",
        type:"POST",
        data:   "groupId="+currentGroupID+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            getData(displayListGroup);
            if(res=="SUCCEED")alert("Bạn đã rời nhóm")
            else alert ("Bạn không là thành viên của nhóm");
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
}

//xử lí xóa thành viên
function deleteMember(){
    onDisplayPlayout("idDeleteMember","display-none",1);
}
let selectFrmDeleteMember = document.getElementById("frmDeleteMember");
selectFrmDeleteMember.addEventListener("submit",e=>{
    e.preventDefault();
    $.ajax({
        url: "/groups/delete/members",
        type:"DELETE",
        data:   $('#frmDeleteMember').serialize()+"&groupId="+currentGroupID+"&email="+user.email,
        dataType:"text",
        success: function(res) {
            console.log(getInfoGroup(currentGroupID));
            if(res=="SUCCEED")alert("Đã xóa thành viên")
            else alert ("Người dùng không tồn tại hoặc bạn không đủ quyền để xóa");
            offPlayout('idAddMember','display-none',1);
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
});