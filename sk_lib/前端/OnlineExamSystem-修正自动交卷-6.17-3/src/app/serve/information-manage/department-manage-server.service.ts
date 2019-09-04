import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class DepartmentManageServerService {

  constructor(private http: HttpClient) {
  }

  addDepartment(body) {
    return this.http.post('/apis/api/root/department', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteDepartment(id) {
    return this.http.delete("/apis/api/root/department/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateDepartment(body) {
    return this.http.put('/apis/api/root/department', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //根据学校查询所有部门
  getDepartment(param) {
    return this.http.get('/apis/api/root/department', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //校管根据学校查询所有部门
  getDepartmentFromAdmin(param) {
    return this.http.get('/apis/api/admin/department', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //教师查询所有通过审核的部门
  getDepartmentFromTeacher() {
    return this.http.get('/apis/api/teacher/department', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

}
