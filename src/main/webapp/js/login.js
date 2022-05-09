function validation()
{
    event.preventDefault();               
    let email=document.querySelector("#email").value;
    let password=document.querySelector("#password").value;
    let paswordError=document.querySelector("#passwordError");
    let emailError=document.querySelector("#emailError");

    if(email.includes("@") && password.length>=8)
    {
         let loading=document.querySelector("#loading");
         loading.style.display='block';
         let formsubmit=document.querySelector("#formsubmit")
         formsubmit.style.display='none';
      loginCredentials={userEmail:email,userPassword:password};
       const url="http://localhost:8080/feesmanagement_web/RegistersServer?email="+email+"&password="+password;
    //    fetch(url);
       axios.post(url,loginCredentials).then(response=>{
            //alert(response.data);
            let result=document.querySelector("#result");
            loading.style.display='none';
            //formsubmit.style.display='block';
            result.innerHTML=response.data;
            //loading.style.display='none';
       });
       
       
    }
    else
    {
        paswordError.innerHTML="invalid"
    }
}
 // let message=document.querySelector("#msg");
    // if(email!=null && password!=null)
    // {
    //     let button=document.querySelector("#submit");
    //     message.innerHTML='Please register';
    //     button.innerHTML='<a href="login.html">register</a>';
    // } 

