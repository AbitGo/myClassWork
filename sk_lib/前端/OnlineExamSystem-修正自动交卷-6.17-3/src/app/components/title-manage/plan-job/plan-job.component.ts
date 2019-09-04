import {Component, OnInit} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd';
import {PlanJobServerService} from '../../../serve/title-manage/plan-job-server.service';

@Component({
  selector: 'app-plan-job',
  templateUrl: './plan-job.component.html',
  styleUrls: ['./plan-job.component.css']
})
export class PlanJobComponent implements OnInit {

  _jobName;
  _jobDescription;
  _startDate = null;
  _endDate = null;
  _dateTime = null;
  _status;
  status;
  planTimes;
  _planTime;

  serachShow = false;
  isVisible = false;

  answer: any;

  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;

  constructor(private _message: NzMessageService,
              private planJobServerService: PlanJobServerService) {
  }

  ngOnInit() {
    this.planTimes = [
      {value: '22', label: '22时'},
      {value: '23', label: '23时'},
      {value: '0', label: '0时'},
      {value: '1', label: '1时'},
      {value: '2', label: '2时'},
      {value: '3', label: '3时'},
      {value: '4', label: '4时'},
      {value: '5', label: '5时'}
    ];
    this.status = [{value: '0', label: '禁用'},
      {value: '1', label: '启用'},
      {value: '2', label: '删除'},
    ];

    this.refreshData(true);
  }

  submit() {
    let time = new Date(this._dateTime);
    let dateTime = '0 0 ' + time.getHours() + ' * * ?';
    const body = {
      description: this._jobDescription,
      planStartDate: this.changeTime(new Date(this._startDate)) == "1970-01-01" ? "" : this.changeTime(new Date(this._startDate)),
      planEndDate: this.changeTime(new Date(this._endDate)) == "1970-01-01" ? "" : this.changeTime(new Date(this._startDate)),
      cronString: this._planTime,
      status: this._status,
    };
    this.planJobServerService.insertJob(body).subscribe((data: any) => {
      console.log('添加');
      console.log(body);
      console.log(data);
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.refreshData(true);
      } else {
        this._message.create('error', "失败");
      }

    });
  }

  //表格数据操作
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    this.planJobServerService.getJob({
      'page': this._current,
      'size': this._pageSize
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
  }

  search() {
    const body = {};
    this.planJobServerService.getRepeat(body).subscribe((data: any) => {
      console.log('添加');
      console.log(data);
      this._message.create('success', data.message);
    });
  }

  sort() {
  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.planJobServerService.deleteJob(id).subscribe((data: any) => {
      // console.log(data)
      this._message.create('success', data.message);
      this.refreshData(true);
    });
    // this.refreshData(true);
  };

  changeTime(time) {
    let year = time.getFullYear() < 10 ? ('0' + time.getFullYear()) : time.getFullYear();
    let month = (time.getMonth() + 1) < 10 ? ('0' + (time.getMonth() + 1)) : (time.getMonth() + 1);
    let day = time.getDate() < 10 ? ('0' + time.getDate()) : time.getDate();
    return year + '-' + month + '-' + day;
  }

}
