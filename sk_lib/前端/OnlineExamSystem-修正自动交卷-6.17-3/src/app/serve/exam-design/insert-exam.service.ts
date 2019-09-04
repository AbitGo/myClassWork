import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class InsertExamService {

  constructor(private http: HttpClient) {
  }

  //考试安排
  insertExam(body) {
    return this.http.post('/apis/api/teacher/exam', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //删除考试
  deleteExam(id) {
    return this.http.delete("/apis/api/teacher/exam/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  //获得考试
  getExam(param) {
    return this.http.get('/apis/api/teacher/exam', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //学生进入考试
  getExamFromStudent(id, param) {
    return this.http.get('/apis/api/student/exam/' + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //学生获得多个考试
  getExamListFromStudent(param) {
    return this.http.get('/apis/api/student/examlist', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
