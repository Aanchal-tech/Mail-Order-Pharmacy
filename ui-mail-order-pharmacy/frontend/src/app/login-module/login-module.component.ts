import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';
import { AuthenticationResponse } from '../authentication-response';
import { AuthService } from '../auth.service';
import { UserDetails } from '../user-details';
@Component({
  selector: 'app-login-module',
  templateUrl: './login-module.component.html',
  styleUrls: ['./login-module.component.css']
})
export class LoginModuleComponent implements OnInit {
  token!: string;
  errMsg!: string;
  user : User={
    'userid':" ",
    'upassword':" ",
    'uname':""
  };
  userDtls!: UserDetails
   //authResponse : any;
  authResponse:AuthenticationResponse ={
    'uid':"",
    'name':"",
    'valid':false
  };
  constructor(private userService:UserService,private authService: AuthService, private router:Router,private route: ActivatedRoute) {

    if(this.authService.isLoggedIn()){
      this.router.navigate(["/drugs"]);
    }

      }


  ngOnInit(): void {}
  onLogin(custForm:NgForm)
  {
    console.log(custForm.value.userid);
    console.log(custForm.value.password);
    this.user.userid=custForm.value.userid;
    this.user.upassword=custForm.value.password;
    // this.user.uname="admin";
    this.userService.loginMember(this.user).subscribe(data => {
      this.errMsg='';
      console.log(data);

      this.userDtls= data as UserDetails;
      localStorage.setItem("userId", this.userDtls.userid);
      localStorage.setItem("token", this.userDtls.authToken);
      console.log(this.userDtls.userid);
      console.log(localStorage.getItem("userId"));
      console.log(this.userDtls.authToken);
      let response=this.userService.validateToken(this.userDtls.authToken);
      response.subscribe(data1=>{
        console.log(data1);
        this.authResponse=data1 as AuthenticationResponse;
        console.log(this.authResponse);
        console.log(this.authResponse.uid, this.authResponse.valid);
        if(this.authResponse.valid){
          console.log("true valid");
          this.authService.login()
        }
        else{
          console.log("false valid");
          this.authService.logout();
        }
        if(this.authService.isLoggedIn()){
          localStorage.setItem('userId',this.authResponse.uid);
          console.log(localStorage.getItem("userId"));
          this.router.navigate(['drugs']);
        }

      });

//localStorage.getItem("token");

    }, error=>{

        this.errMsg = "Invalid Credentials!"
    })
    custForm.reset();
  }
}
