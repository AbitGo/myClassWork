import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GradeQueryServerService} from '../../../serve/information-query/grade-query-server.service';

@Component({
  selector: 'app-grade-query',
  templateUrl: './grade-query.component.html',
  styleUrls: ['./grade-query.component.css']
})
export class GradeQueryComponent implements OnInit {

  validateForm: FormGroup;
  searchData: FormGroup;

  /*下拉框*/
  schools = [];
  departments;
  classes;
  exams;
  courses;

  statusShow = false;
  /*状态*/
  id;
  /*删除的id*/
  tabTitle = '';
  /*弹窗标题*/
  scholls;
  schollsId;
  serachShow = false;
  /*控制是否生成table*/
  isVisible = false;
  /*tab添加修改窗口*/

  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;
  _filterGender = [
    {name: 'male', value: false},
    {name: 'female', value: false}
  ];

  constructor(private _randomUser: TableServiceService,
              private fb: FormBuilder,
              private gradeQueryServerService: GradeQueryServerService) {
  }

  ngOnInit() {

    this.refreshData();

  }


  sort(value) {
    this._sortValue = value;
    this.refreshData();
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
    this.refreshData(true);
  }


  /*表格数据操作*/
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this.gradeQueryServerService.getScoreFromStudent({
      'page': this._current,
      'size': this._pageSize
    }).subscribe((data: any) => {
      console.log(data);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });

  }

}
