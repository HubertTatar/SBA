import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {User} from '../models/user';

@Injectable()
export class UserService {
  user: User[];

  constructor(private http: Http){}

  getUserByName(currentUserName: string) {
    let tokenUrl = 'http://localhost:8080/rest/user/userName';
    let headers = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem('token')})
    return this.http.post(tokenUrl, currentUserName, {headers: headers});
  }

  updateUser(user){
    let tokenUrl1 = "http://localhost:8080/rest/user/update";
    let headers1 = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
    return this.http.post(tokenUrl1, JSON.stringify(user), {headers: headers1});
  }
}
