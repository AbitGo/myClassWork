import {Injectable} from '@angular/core';
import {HttpHeaders} from "@angular/common/http";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PlanJobServerService {

  constructor(private http: HttpClient) {
  }

  insertJob(body) {
    return this.http.post('/apis/api/teacher/schedule/insertJob', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteJob(id) {
    return this.http.delete("/apis/api/teacher/schedule/deleteJob/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateJob(body) {
    return this.http.put('/apis/api/teacher/schedule/updateJob', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getJob(param) {
    return this.http.get('/apis/api/teacher/schedule/findJobs', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //查询重复
  getRepeat(body) {
    return this.http.post('/apis/api/teacher/question/checkRepeat', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

}
