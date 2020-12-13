let upload = document.getElementById('image_upload_form');
  upload.addEventListener('submit', (event) =>{
    event.preventDefault();
  
    var data = new FormData(upload);
    data.append("name", chatting);
    console.log(data);
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
    let mes =aMessage(chatting,);
  });

  function aMessage(recipient,image){
    let chatId = getChatId(recipient,sender);
    
    return JSON.stringify({
      recipient: recipient,
      sender: user.email,
      image: image,
      chatId:chatId.id,
      type:chatId.type,
      mesType:'Image'
    })
  }