const selectIdFrmUpdateInfo = document.getElementById('id-frm-updata-info');



// xử lí sự kiện sửa thông tin người dùng
selectIdFrmUpdateInfo.addEventListener("submit",e =>{
    e.preventDefault();
    // sử lí chính sửa thông tin
    var a =  $('#id-frm-updata-info').serialize();
    console.log(a);
    $.ajax({
        url: "/users/edit",
        type:"PUT",
        data: a,
        dataType:"text",
        success: function(res) {
            getData();
            alert("cập nhật thành công");
            offDisplayFormUpdate();
        },
         error: () =>{
            alert("Incorrect!");
         }
    });
});
// hàm hiển thị thông tin cá nhân và đăng xuất
function displayProfile(){
    selectListFriend.style.display="none";
    selectBoxInfoUser.style.display ="flex";
    document.getElementById('myName').innerText = "Tên : "+user.userName;
    document.getElementById('myAge').innerText = "Tuổi : "+user.age;
    let gt;
    if(user.gender==="FEMALE") gt = "Nữ"
    else gt = "Nam";
    document.getElementById("myGender").innerText = "Giới tính : "+gt;
    document.getElementById('myEmail').innerText = "Địa chỉ Email : "+user.email;

    offPlayout('btnAddGroup','display-none',0);// tắt nút thêm nhóm

}