import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class StudentManageServerService {

  constructor(private http: HttpClient) {
  }

  addStudent(body) {
    return this.http.post('/apis/api/admin/user/addstudent', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteStudent(param) {
    return this.http.delete('/apis/api/admin/user/deletestudent', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  updateStudent(body) {
    return this.http.put('/apis/api/admin/user/updatestudent', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //查询所有学校管理员
  getStudent(param) {
    return this.http.get('/apis/api/admin/user/liststudent', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //教师根据班级获得学生
  getStudentFromTeacher(param) {
    return this.http.get('/apis/api/teacher/user/liststudent', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //下载模板
  downloadStudentFile(param) {
    return this.http.get('/apis/api/file/downloadstudent', {
      withCredentials: true
    });
  }

}
