async  function VerifyAccount() {
    
     const dto = {
        verification: document.getElementById("verificationCode").value
    }

    console.log(user_dto);

    const response = await fetch("Verify_Account", {method: "POST", body: JSON.stringify(dto), headers: {"Content-Type": "application/json"}});

    if (response.ok) {
        const json = await response.json;
        console.log(json);

        if (json.success) {
            window.location = "index.html";
        } else {

            document.getElementById("message").innerHTML = json.content;
        }

    } else {
        console.log("Error");
    }
    
}