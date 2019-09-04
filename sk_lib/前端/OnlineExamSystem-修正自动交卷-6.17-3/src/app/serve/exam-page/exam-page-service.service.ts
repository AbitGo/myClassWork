import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class ExamPageServiceService {

  constructor(private http: HttpClient) {
  }

  //提交考试答案
  submitExamAnswer(id, body) {
    return this.http.post('/apis/api/student/exam/' + id, body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //获取学生考试时间
  getExamtime(param, id) {
    return this.http.get('/apis/api/student/examTime/' + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
