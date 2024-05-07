function verify() {
var password1 = document.forms['form']['password'].value;
var password2 = document.forms['form']['verifyPassword'].value;
var username = document.forms['form']['username'].value; 
var errorElement = document.getElementById("error");
    if (username.length <= 10) {
        errorElement.innerHTML = "Username must be longer than 10 characters";
        return false;
    }

if (password1 == null || password1 == "" || password1 != password2 || password1.length() <10 ) {
document.getElementById("error").innerHTML = "Please check your passwords";
return false;
}

return true;


}
