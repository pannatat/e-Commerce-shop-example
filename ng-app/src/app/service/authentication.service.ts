import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Headers} from '@angular/http';
import {Response} from '@angular/http';

@Injectable()
export class AuthenticationService {
  private authUrl = 'http://localhost:8080/auth';
  private headers = new Headers({'Content-Type':'application/json'})
  constructor(private http:Http) { }

  login(username:string, password:string): Observable<boolean>{
    return this.http.post(this.authUrl,JSON.stringify({username:username,password:password}),{headers:this.headers})
      .map((response:Response) => {
        let token = response.json() && (response.json() as any).token;
        if(token){
          localStorage.setItem('currentUser',JSON.stringify({username:username,token:token}));
          let actor = response.json().actor;
          console.log(actor);
          localStorage.setItem('userDetails',JSON.stringify(actor));
          return true;
        }else {
          return false;
        }
      })
    //.catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getToken():string{
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token:"";
  }

  logout():void{
    localStorage.removeItem('currentUser');
    localStorage.removeItem('userDetails');
  }

  getCurrentUser() {
    let details = localStorage.getItem('userDetails');
    if (details == null || details.length == 0) {
      return null;
    }
    return JSON.parse(localStorage.getItem('userDetails'));
  }

  hasRole(role: String): boolean {
    let user: any = this.getCurrentUser();
    if (user) {
      let roleList: string[] = role.split(',');
      for (let j = 0; j < roleList.length; j++) {
        let authList = user.authorities;
        let userRole = 'ROLE_' + roleList[j].trim().toUpperCase();
        for (let i = 0; i < authList.length; i++) {
          if (authList[i].name == userRole) {
            return true;
          }
        }
      }
      return false;
    }

  }

}
