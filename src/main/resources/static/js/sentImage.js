let upload = document.getElementById('image_upload_form');
  upload.addEventListener('submit', (event) =>{
    event.preventDefault();
  
    let img = new FormData(upload);
    let data =aImage(chatting,img);
    $.ajax({
          type: "POST",
          enctype: 'multipart/form-data',
          url: "/messages/img",
          data: data,
  
          // prevent jQuery from automatically transforming the data into a query string
          processData: false,
          contentType: false,
          cache: false,
          timeout: 1000000,
          success: function(data, textStatus, jqXHR) {
              console.log(data);
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.log("lỗi rồi bà con ơi!!");
  
          }
      });
   
  });

  function aImage(recipient,image){
    let chatId = getChatId(recipient);
    
    let a = `recipient=${recipient}&sender=${user.email}&image=${image}&chatId:${chatId.id}&type:${chatId.type}&mesType:'Image'`;
    return a;
  }