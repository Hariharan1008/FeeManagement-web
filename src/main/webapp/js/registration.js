function registerValidation()
     {
    	 event.preventDefault();
         let loading=document.querySelector("#loading");
         loading.style.display='block';
         let registrationTab=document.querySelector("#registrationDetails");
         let name=document.querySelector("#name").value;
         let userName=document.querySelector("#username").value;
         let mobile=document.querySelector("#mobile").value;
         let age=document.querySelector("#age").value;
         let email=document.querySelector("#email").value;
         let password=document.querySelector("#password").value;
         const url="http://localhost:8080/feesmanagement_web/RegisterServlet?name="+name+"&username="+userName+"&mobile="+mobile+"&age="+age+"&email="+email+"&password="+password;
         fetch(url).then(res=>res.json()).then(res=>{
        	 let registered=res;
             loading.style.display='none';
             if(registered=="successfull")    
             {
                 document.querySelector("#result").innerHtml="Success";
                 registrationTab.style.display='none';
                 
             }     
        	 
        	 
        
           //  let result=document.querySelector("#result");
             //loading.style.display='none';
             //formsubmit.style.display='block';
             //result.innerHTML=response.data;
             //loading.style.display='none';
             
        });
        
        
        
     }