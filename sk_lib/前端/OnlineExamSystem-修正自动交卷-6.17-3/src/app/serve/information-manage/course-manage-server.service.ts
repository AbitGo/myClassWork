import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class CourseManageServerService {

  constructor(private http: HttpClient) {
  }

  addCourse(body) {
    return this.http.post('/apis/api/admin/subject', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteCourse(id) {
    return this.http.delete("/apis/api/admin/subject/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateCourse(body) {
    return this.http.put('/apis/api/admin/subject', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //查询所有学校管理员
  getCourse(param) {
    return this.http.get('/apis/api/admin/subject', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  /**
   * 如下为教师对科目进行增删改查
   */

  addCourseFromTeacher(body) {
    return this.http.post('/apis/api/teacher/subject', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  deleteCourseFromTeacher(id) {
    return this.http.delete("/apis/api/teacher/subject/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  updateCourseFromTeacher(body) {
    return this.http.put('/apis/api/teacher/subject', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  getCourseFomTeacher(param) {
    return this.http.get('/apis/api/teacher/subject', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  getSubjectFromStudent(param){
    return this.http.get('/apis/api/student/subject', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

}
