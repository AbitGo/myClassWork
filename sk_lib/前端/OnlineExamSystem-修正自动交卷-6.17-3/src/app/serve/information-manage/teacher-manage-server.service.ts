import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class TeacherManageServerService {

  constructor(private http: HttpClient) {
  }

  addTeacher(body) {
    return this.http.post('/apis/api/admin/user/addteacher', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteTeacher(param) {
    return this.http.delete('/apis/api/admin/user/deleteteacher', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  updateTeacher(body) {
    return this.http.put('/apis/api/admin/user/updateteacher', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //查询所有学校管理员
  getTeacher(param) {
    return this.http.get('/apis/api/admin/user/listteacher', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
