import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Photo } from '../models/photo';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AddPhotoService {

  constructor(private http: Http){}

  sendPhoto(photo: Photo) {
    let url = 'http://localhost:8080/rest/photo/add';
    let headers1 = new Headers({'Content-Type': 'application/json', 'Authorization':'Bearer ' + localStorage.getItem('token')});
    return this.http.post(url, JSON.stringify(photo), {headers: headers1});
  }
}
