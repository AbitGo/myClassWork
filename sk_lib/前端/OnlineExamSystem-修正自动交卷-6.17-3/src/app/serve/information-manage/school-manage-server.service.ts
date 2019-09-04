import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class SchoolManageServerService {

  constructor(private http: HttpClient) {
  }

  addSchool(body) {
    return this.http.post('/apis/api/root/school/add', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteSchool(param) {
    return this.http.delete("/apis/api/root/school/delete", {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  updateSchool(body) {
    return this.http.put('/apis/api/root/school/update', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //查询所有学校管理员
  getSchool(param) {
    return this.http.get('/apis/api/root/school/list', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
