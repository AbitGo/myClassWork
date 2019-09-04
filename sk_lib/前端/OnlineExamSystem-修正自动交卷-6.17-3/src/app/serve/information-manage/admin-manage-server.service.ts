import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {HttpParams} from "@angular/common/http";

@Injectable()
export class AdminManageServerService {

  constructor(private http: HttpClient) {
  }

  addAdmin(body) {
    return this.http.post('/apis/api/root/user/addrooter', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  updateAdmin(body) {
    return this.http.put('/apis/api/root/user/updaterooter', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getAdmin(param, sortField, sortOrder, genders, url) {

    return this.http.get(url, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  deleteAdmin(param, url) {
    return this.http.delete(url, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
