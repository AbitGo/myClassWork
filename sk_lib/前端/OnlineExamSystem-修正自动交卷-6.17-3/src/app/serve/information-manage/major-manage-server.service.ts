import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class MajorManageServerService {

  constructor(private http: HttpClient) {
  }

  addMajor(body) {
    return this.http.post('/apis/api/root/major', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteMajor(id) {
    return this.http.delete("/apis/api/root/major/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateMajor(body) {
    return this.http.put('/apis/api/root/major', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //根据学校查询所有部门
  getMajor(param) {
    return this.http.get('/apis/api/root/major', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //根据学校查询所有部门
  getMajorFromAdmin(param) {
    return this.http.get('/apis/api/admin/major', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //根据学校查询所有已审核的专业
  getAdultMajorFromAdmin(param) {
    return this.http.get('/apis/api/admin/major/adult', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //科目绑定专业
  bindMajor2Subject(body) {
    return this.http.post('/apis/api/admin/major/bindsubject', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //删除绑定科目
  deleteBindSubject(param, id) {
    return this.http.delete("/apis/api/admin/major2subject/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //查询绑定科目
  getBindSubject(param) {
    return this.http.get('/apis/api/admin/major2subject', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }


  //学校管理员添加专业
  addMajorFromAdmin(body) {
    return this.http.post('/apis/api/admin/major', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //教师通过学院获得所有专业
  getMajorFromTeacher(param) {
    return this.http.get('/apis/api/major/adult', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }


}
