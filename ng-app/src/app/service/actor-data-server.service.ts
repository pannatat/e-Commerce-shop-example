import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers, Response, URLSearchParams} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/mergeMap';
import {AuthenticationService} from './authentication.service';
import {Actor} from "../Actor/actor";
@Injectable()
export class ActorDataServerService{
  constructor(private http: Http, private authenticationService: AuthenticationService){

  }

  getActorsData() {
    let actorArray: Actor[];
    let headers = new Headers({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    });
    return this.http.get('http://localhost:8080/actor', {headers: headers})
      .map(res => res.json());
  }


  getActor(id: number) {
    let actor: Actor;
    let headers = new Headers({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    });
    return this.http.get('http://localhost:8080/actor/' + id, {headers: headers})
      .map((res: Response) => {
        if (res) {
          if (res.status === 200) {
            return res.json();
          }
          if (res.status === 204) {
            return null;
          }
        }
      });
  }


  addActor(actor: Actor, file: any): Observable<Actor> {
    const formData = new FormData();
    let fileName: string;
    formData.append('file', file);
    let header = new Headers({'Authorization': 'Bearer ' + this.authenticationService.getToken()});
    let options = new RequestOptions({headers: header});

    return this.http.post('http://localhost:8080/upload', formData,options)
      .flatMap(filename => {
        let body = JSON.stringify(actor);
        header.append('Content-Type','application/json');
        options = new RequestOptions({headers: header});
        return this.http.post('http://localhost:8080/actor', body, options)
          .map(res => {
            return res.json()
          })
      });
  }
}



