let upload = document.getElementById('image_upload_form');
  upload.addEventListener('submit', (event) =>{
    event.preventDefault();
  
    let img = new FormData(upload);
    let chatId = getChatId(chatting);
    let data = {};
    data.append('recipient',chatting);
    data.append('sender',user.email);
    data.append('type',chatId.type);
    data.append('image',img);
    data.append('mesType','Image');
    data.append('chatId',chatId.id);
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