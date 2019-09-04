import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class TitleManageServerService {

  constructor(private http: HttpClient) {
  }

  addTitle(body) {
    return this.http.post('/apis/api/teacher/question', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  addTitleFromStudent(body) {
    return this.http.post('/apis/api/student/question', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteTitle(id) {
    return this.http.delete("/apis/api/teacher/question/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateTitle(body) {
    return this.http.put('/apis/api/teacher/question', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getTitle(param) {
    return this.http.get('/apis/api/teacher/question', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  adultTitle(body, id) {
    return this.http.post('/apis/api/teacher/adult/' + id, body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getTitleById(param,id) {
    return this.http.get('/apis/api/student/question/'+id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
