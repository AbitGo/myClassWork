import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class LeadershipManageServerService {

  constructor(private http: HttpClient) {
  }

  addLeadership(body) {
    return this.http.post('/apis/api/root/user/addadmin', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteLeadership(param) {
    return this.http.delete('/apis/api/root/user/deleteadmin', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  updateLeadership(body) {
    return this.http.put('/apis/api/root/user/updateadmin', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //查询所有学校管理员
  getLeadership(param) {
    return this.http.get('/apis/api/root/user/listadmin', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }


}
