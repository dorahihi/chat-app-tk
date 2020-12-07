
// sự kiện gửi tin 
let selectSend = document.getElementById('send');
let selectMessage = document.getElementById("message");
selectSend.addEventListener('click',e=>{    
    let message= selectMessage.value;
    if (message !=""){
       // selectMessage.value ="";
        insertMessage(message,0);
    }
        
    // gửi tin
});
selectMessage.addEventListener('keyup',e=>{    
    let message= selectMessage.value;
    if(e.keyCode =="13" &&  message!=""){
        
       // selectMessage.value ="";
        insertMessage(message,0);    
        //gửi tin
    }
});
