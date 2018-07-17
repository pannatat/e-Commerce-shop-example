import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    //reset login status
    this.authenticationService.logout();
  }

  login(){
    this.loading = true;
    this.authenticationService.login(this.model.username,this.model.password)
      .subscribe(result => {
        if(result === true){
          this.router.navigate(['list']).then($=>window.location.reload());
        }else {
          this.error = 'Username or password is incorrect';
          this.loading = false;
        }
      },error =>{
        this.loading = false;
        this.error = error;
      });
  }


}
