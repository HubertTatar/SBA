import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { User } from '../models/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UploadPhotoService {
  filesToUpload: Array<File>;

  constructor() {
    this.filesToUpload = [];
  }

  upload() {
    this.makeFileRequest("http://localhost:8080/rest/photo/upload", [], this.filesToUpload).then(
      (result) => {
        console.log(result)
      },
      (error) => {
        console.log(error);
      }
    );
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>> fileInput.target.files;
  }

  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
    return new Promise(
      (resolve, reject) => {
        var formData: any = new FormData();
        var xhr = new XMLHttpRequest();
        for(var i = 0; i < files.length; i++) {
          formData.append("uploads[]", files[i], files[i].name);
        }
        xhr.onreadystatechange = function () {
          if (xhr.readyState == 4) {
            if (xhr.status == 200) {
              console.log('upload succesfull');
            } else {
              reject(xhr.response);
            }
          }
        }
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem('token'));
        xhr.send(formData);

      }
    );
  }
}
