import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class GradeQueryServerService {

  constructor(private http: HttpClient) {
  }

  //学生获取成绩
  getScoreFromStudent(param) {
    return this.http.get('/apis/api/student/score', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //教师获取学生成绩
  getScoreFromTeacher(param) {
    return this.http.get('/apis/api/teacher/score', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  getsSchools(callback) {
    return this.http.get('/api/school_all').subscribe(data => callback(data))
  };

  getDepartment(params, callback) {
    this.http.post('/api/school2department', JSON.stringify(params), {

      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    }).subscribe(data => {
      callback(data);
    })
  }
}
