import {Injectable} from '@angular/core';
import {HttpHeaders} from "@angular/common/http";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ErrorTitleService {

  constructor(private http: HttpClient) {
  }

  //获取错题
  getErrorQuestion(param) {
    return this.http.get('/apis/api/student/errorquestion', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //删除错题
  removeErrorQuestion(id) {
    return this.http.delete("/apis/api/student/errorquestion/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  //添加错题练习
  insertErrorTitlePractice(body) {
    return this.http.post('/apis/api/student/exercise', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //提交错题练习
  submitErrorTitlePractice(id, body) {
    return this.http.post('/apis/api/student/exercise/submit/' + id, body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }


}
