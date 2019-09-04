import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable()
export class LoginServeService {

  constructor(private http: HttpClient) {
  }

  login(params, callback) {
    // console.log(JSON.stringify(params))
    this.http.post('/apis/login', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  logout(params, callback) {
    this.http.post('/apis/logout', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一个题目历史得分情况
  changePassword(body) {
    return this.http.post('/apis/api/user/changePassword', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });

  }

}
