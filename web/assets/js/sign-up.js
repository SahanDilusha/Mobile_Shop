async function SignUp() {
    
    const user_dto = {
        firstName:document.getElementById("first_name").value,
        lastName:document.getElementById("last_name").value,
        email:document.getElementById("email").value,
        password:document.getElementById("password").value,
    }
    
  const response = await fetch("SignUp",{method:"POST",body:JSON.stringify(user_dto),headers:{"Content-Type":"application/json"}});
    
    if (response.ok) {
        const json = await response.json;
        console.log(json);
    } else {
        console.log("Error");
    }
}