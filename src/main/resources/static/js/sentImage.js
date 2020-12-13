let upload = document.getElementById('image_upload_form');
  upload.addEventListener('submit', (event) =>{
    event.preventDefault();
  
    let img = new FormData(upload);
    let chatId = getChatId(chatting);
    img.append('recipient',chatting);
    img.append('sender',user.email);
    img.append('type',chatId.type);
    img.append('image',img);
    img.append('mesType','Image');
    img.append('chatId',chatId.id);
    console.log(img);
    $.ajax({
          type: "POST",
          enctype: 'multipart/form-data',
          url: "/messages/img",
          data: img,
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