import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class KnowledageManageServiceService {

  constructor(private http: HttpClient) {
  }

  addKnowledage(body) {
    return this.http.post('/apis/api/teacher/knowledge', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteKnowledage(id) {
    console.log("------>" + id);
    return this.http.delete("/apis/api/teacher/knowledge/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateKnowledage(body) {
    return this.http.put('/apis/api/teacher/knowledge', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getKnowledage(param) {
    return this.http.get('/apis/api/teacher/knowledge', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
