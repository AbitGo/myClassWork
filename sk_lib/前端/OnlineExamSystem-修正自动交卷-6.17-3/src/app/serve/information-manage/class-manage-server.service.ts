import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class ClassManageServerService {

  constructor(private http: HttpClient) {
  }

  //学校管理员添加班级
  addClass(body) {
    return this.http.post('/apis/api/admin/class', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //学校管理员删除班级
  deleteClass(id) {
    return this.http.delete("/apis/api/admin/class/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  //学校管理员更新班级
  updateClass(body) {
    return this.http.put('/apis/api/admin/class', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //学校管理员根据学校查询所有部门
  getClass(param) {
    return this.http.get('/apis/api/class', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //教师获得所有所属的班级
  getClassFromTeacher(param) {
    return this.http.get('/apis/api/class', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
