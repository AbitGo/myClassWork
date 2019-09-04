import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SchoolManageServerService} from '../../../serve/information-manage/school-manage-server.service';
import {HttpClient} from '@angular/common/http';
import {SessionStorageService} from 'ngx-webstorage';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-school-manage',
  templateUrl: './school-manage.component.html',
  styleUrls: ['./school-manage.component.css']
})

export class SchoolManageComponent implements OnInit {
  validateForm: FormGroup;

  name;
  teacherNum;

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
              private sessionSt: SessionStorageService,
              private schoolManageServerService: SchoolManageServerService,
              private _message: NzMessageService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      name: ['', [Validators.required]],
      teacherNum: ['', [Validators.required]],
    });
    this.searchSchool();
  }

  //查询数据
  searchSchool() {
    // console.log(this.schollsId);
    this.serachShow = true;
    this.refreshData(true);
  }

  /*弹窗*/
  operateData(strs) {
    this.isVisible = true;
    if (strs == 'add') {
      this.tabTitle = '添加学校数据';
      this.statusShow = false;
    } else {
      this.tabTitle = '修改学校数据';
      this.statusShow = true;
      this.id = strs.id;
      this.name = strs.name;
      this.teacherNum = strs.teacherNum;
    }
    // console.log(strs);
  }

  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id,
        name: this.name,
        teacherNum: this.teacherNum,
      };
      this.schoolManageServerService.updateSchool(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchSchool();
        }     else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      const body = {name: this.name, teacherNum: this.teacherNum};
      this.schoolManageServerService.addSchool(body).subscribe((data: any) => {
        console.log('添加');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchSchool();
        }   else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    }
    // console.log(this.validateForm.value);
    this.validateForm.reset();
    // console.log(this.validateForm.value)
    this.isVisible = false;
    // this.refreshData(true);
    this.searchSchool();
    /*刷新table*/
  }

  //添加数据
  // addSchool() {
  //   if (this.validateForm.valid) {
  //     /*此处提交*/
  //     console.log(this.validateForm.value);
  //     this.validateForm.reset();
  //     console.log(this.validateForm.value)
  //     this.isVisible = false;
  //     this.refreshData(true);
  //     /*刷新table*/
  //
  //   }
  // }

  //关闭窗口
  handleCancel() {
    this.isVisible = false;
  }

  sort(value) {
    this._sortValue = value;
    this.refreshData(true);
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
    this.refreshData(true);
  }


  //表格数据操作
  refreshData(reset) {
    // if (reset) {
    //   this._current = 1;
    // }
    // this._loading = true;
    // const selectedGender = this._filterGender.filter(item => item.value).map(item => item.name);
    // this._randomUser.getUsers(this._current, this._pageSize, 'name', this._sortValue, selectedGender, '/api/school').subscribe((data: any) => {
    //   this._loading = false;
    //   this._total = data[0].info.total;
    //   this._dataSet = data[0].results;
    //   console.log(data)
    // })

    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    this.schoolManageServerService.getSchool({
      'page': this._current,
      'size': this._pageSize
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    this.schoolManageServerService.deleteSchool({id: id}).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchSchool();
      }   else {
        this._message.create('error', "失败");
      }
    });
    this.searchSchool();
  };

}
