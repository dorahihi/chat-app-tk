const openSignupBtn = document.getElementById('open-signup-btn');
const haveAccount = document.getElementById('have-account');
const login = document.getElementById('login');
const signup = document.getElementById('signup');
const loginForm = document.getElementById('login-form');
const signupForm = document.getElementById('signup-form');
const mainContent = document.getElementById('main-content');

const notiContainer = document.getElementById('noti-container');
const notiBox = document.getElementById('noti-box');
const notiContent = document.getElementById('noti-content');
const notiBtn = document.getElementById('noti-btn');
const type = document.getElementById('type');

const welcome = document.getElementById('welcome');
const welcomeLoginBtn = document.getElementById('welcome-login-btn');
const welcomeSignupBtn = document.getElementById('welcome-signup-btn');

const gender = document.getElementById('gender');

const loading = document.getElementById('loading-box');

let pageType = type.innerHTML;
console.log(pageType);

const url = "https://chatapp-kkt.herokuapp.com/";
const local = "http://localhost:8080/";

document.onreadystatechange = function() {
    if (document.readyState !== "complete") {
        openLoading();
    } else {
        closeLoading();
    }
};

const openLoading = () =>{
  loading.classList.remove('hide-o');
}

const closeLoading = () => {
  loading.classList.add('hide-opacity');
  setTimeout(() => {
    loading.classList.remove('hide-opacity')
      loading.classList.add('hide-o');
  }, 1500);
}

gender.addEventListener('change', () => {

  if(gender.value === "0")
    gender.style.color = "#8C8B8B";
  else gender.style.color = "black";

});

window.onpopstate = e =>{
  let path = window.location.pathname;
  switch (path) {
    case "/login":
      openLoginForm(false);
      break;
    case "/signup":
      openSignupForm(false);
      break;
    default:
    openWelcomePage(true);
  }
};

welcomeLoginBtn.addEventListener('click', () =>{
  openLoginForm(true);
  console.log("pathname: "+window.location.pathname);
});

welcomeSignupBtn.addEventListener('click', () =>{
  welcome.classList.add('hide-o');
  mainContent.classList.remove('hide-o');
  openSignupForm(true);
  console.log("pathname: "+window.location.pathname);
});


openSignupBtn.addEventListener('click', () => {

  let label = openSignupBtn.innerHTML;

  if(label === 'Create Account'){
    openSignupForm(true);
  }
  else {
    openLoginForm(true);
  }

}, true);

const openWelcomePage = (t) =>{
  mainContent.classList.add('hide-o');
  welcome.classList.remove('hide-o');
  if(t) window.history.pushState("Welcome to chatApp", "", "/");
  document.title = "Welcome to chatApp";
}

const openSignupForm = (t) =>{
  welcome.classList.add('hide-o');
  mainContent.classList.remove('hide-o');
  openSignupBtn.innerHTML = "Login";
  haveAccount.innerHTML = 'Already have an account?';
  signup.classList.remove('hide-o');
  login.classList.add('hide-o');
  if(t) window.history.pushState("Signup to chatApp","", "/signup");
  document.title = "Signup to chatApp";
}

const openLoginForm = (t) =>{
  welcome.classList.add('hide-o');
  mainContent.classList.remove('hide-o');
  openSignupBtn.innerHTML = 'Create Account';
  haveAccount.innerHTML = "Don't have an account?";
  login.classList.remove('hide-o');
  signup.classList.add('hide-o');
  if(t) window.history.pushState("Login to chatApp", "", "/login");
  document.title = "Login to chatApp";
}

const notiMessage = message =>{
  notiContainer.classList.remove('hide-o');
  notiContent.innerHTML = message;
}

const redirect = (path) =>{
  console.log("hiihih");
  notiContainer.classList.add('hide-o');
  window.location.replace(url+path);
}

switch (pageType) {
  case 'login':
    welcome.classList.add('hide-o');
    mainContent.classList.remove('hide-o');
    openLoginForm();
    window.history.pushState(" ", "Login to chatApp", "/login");
    break;
  case 'signup':
    welcome.classList.add('hide-o');
    mainContent.classList.remove('hide-o');
    openSignupForm();
    break;
  default:

}

loginForm.addEventListener('submit', e => {
  openLoading();
    e.preventDefault();

    $.ajax({
       type: "POST",
       url: url+ "auth",
       data: $('#login-form').serialize(),
       success: function() {
         closeLoading();
         notiMessage("Login successfully!");
       },
       error: () =>{
         closeLoading();
         notiMessage("Incorrect email or password!");
       }
    });
});

signupForm.addEventListener('submit', e => {
  openLoading();
  e.preventDefault();

  $.ajax({
    type: "POST",
    url: url+ "signup",
    data: $('#signup-form').serialize(),
    //contentType: "application/json",
    success: function() {
      closeLoading();
      notiMessage("Signup successfully!");
      setTimeout(() => {
        redirect('login');
      }, 3000);
    },
    error: () =>{
      closeLoading();
      notiMessage("Something wrong. Please try again!");
    }
  });
});
