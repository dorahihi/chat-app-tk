var url ='https://chatapp-kkt.herokuapp.com/auth';


var loginForm =  document.getElementById('login-form');
loginForm.addEventListener('submit', e => {
      e.preventDefault();
  
      $.ajax({
         type: "POST",
         url: url,
         data: $('#login-form').serialize(),
         success: function(output,status,res) {
            
            if (output==="")window.open("./static/main.html","_self")
            else alert("sai thong tin");
         },
         error: () =>{
            alert("Incorrect email or password!");
         }
      });
});
