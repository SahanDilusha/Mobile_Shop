async function SignUp() {
    
   
    const user_dto = {
        firstName:document.getElementById("firstName").value,
        lastName:document.getElementById("lastName").value,
        email:document.getElementById("email").value,
        password:document.getElementById("password").value,
    }
    
  const response = await fetch("SignUp",{method:"POST",body:JSON.stringify(user_dto),headers:{"Content-Type":"applictaion/json"}});
    
}