
//   $("image_upload_form").addEventListener('submit', (event) =>{
//     event.preventDefault();
  
//     var data = new FormData(this);
//     data.append("name", chatting);
//     console.log(data);
//     $.ajax({
//           type: "POST",
//           enctype: 'multipart/form-data',
//           url: "/messages/img",
//           data: data,
  
//           // prevent jQuery from automatically transforming the data into a query string
//           processData: false,
//           contentType: false,
//           cache: false,
//           timeout: 1000000,
//           success: function(data, textStatus, jqXHR) {
  
//               console.log(data);
//           },
//           error: function(jqXHR, textStatus, errorThrown) {
//             console.log("lỗi rồi bà con ơi!!");
  
//           }
//       });
//   });