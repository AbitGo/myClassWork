import {Component, OnInit} from '@angular/core';
import {DataAnalysisServiceService} from '../../../serve/data-analysis-service.service';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';

@Component({
  selector: 'app-get-analysis',
  templateUrl: './get-analysis.component.html',
  styleUrls: ['./get-analysis.component.css']
})
export class GetAnalysisComponent implements OnInit {
  // 下拉框数据
  semesters;

  department;
  departments;
  major;
  majors;
  class;
  classes;
  student;
  students;

  _startTime = null;
  _endTime = null;

  option;
  option1;
  option2;
  option3;
  option4;


  constructor(private dataAnalysisService: DataAnalysisServiceService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private classManageServerService: ClassManageServerService) {
  }

  ngOnInit() {

    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });

  }

  // 教师根据学院查询专业
  queryMajor(department) {
    this.majorManageServerService.getMajorFromTeacher({
      // 'page': 1,
      // 'size': 10,
      'departmentId': department,
    }).subscribe((data: any) => {

      console.log(data.data);
      this.majors = data.data;
    });
  }

  // 教师根据学院和专业查询班级
  queryClass(major) {
    this.classManageServerService.getClassFromTeacher({
      // 'page': 1,
      // 'size': 10,
      'departmentId': this.department,
      'majorId': major,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.classes = data.data.list;
    });
  }

  //教师根据班级获得学生
  queryStudent(cls) {
    this.studentManageServerService.getStudentFromTeacher({
      'classId': cls,
    }).subscribe((data: any) => {

      console.log(data);
      this.students = data.data;
    });
  }


  submit() {
    let time1 = this.changeTime(new Date(this._startTime));
    let time2 = this.changeTime(new Date(this._endTime));

    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('classId', this.class);
    urlSearchParams.append('subjectId', sessionStorage.getItem('selectedSubject'));
    let param = urlSearchParams.toString();

    //班级多次考试学生成绩分布
    this.dataAnalysisService.showAnalysisFromClassStudentManyTest(param, data => {
      // if (data.success) {
      console.log(this.class + '班级多次考试学生成绩分布');
      console.log(data);
      this.option2 = this.getDataAnalysisOption2(data.data);
    });


    //一个学生多次考试成绩分布
    let urlSearchParams3 = new URLSearchParams();
    urlSearchParams3.append('studentId', this.student);
    urlSearchParams3.append('startTime', time1);
    urlSearchParams3.append('endTime', time2);
    urlSearchParams3.append('subjectId', sessionStorage.getItem('selectedSubject'));
    let param3 = urlSearchParams3.toString();
    this.dataAnalysisService.showAnalysisFromStudentManyTest(param3, data => {
      // if (data.success) {
      console.log(this.class + '一个学生多次考试成绩分布');
      console.log(data);
      this.option1 = this.getDataAnalysisOption1(data.data);
    });

  }


  // searchGetAnalysis() {
  //   // alert(this.department.label);
  //   // console.log(this.department.label);
  //   this.option1 = this.dataAnalysisService.getDataAnalysisOption1();
  //   this.option2 = this.dataAnalysisService.getDataAnalysisOption2();
  //   this.option3 = this.dataAnalysisService.getDataAnalysisOption3();
  //   this.option4 = this.dataAnalysisService.getDataAnalysisOption4();
  // }

  //格式化时间为2018-10-8格式
  changeTime(time) {
    let year = time.getFullYear() < 10 ? ('0' + time.getFullYear()) : time.getFullYear();
    let month = (time.getMonth() + 1) < 10 ? ('0' + (time.getMonth() + 1)) : (time.getMonth() + 1);
    let day = time.getDate() < 10 ? ('0' + time.getDate()) : time.getDate();
    return year + '-' + month + '-' + day;
  }


  getDataAnalysisOption1(data) {

    let examName = [];
    let scoreRatio = [];
    for (let entry of data) {
      examName.push(entry.examName);
      scoreRatio.push(entry.scoreRatio);
    }
    console.log(examName + '->' + scoreRatio);

    let option = {
      title: {
        left: 'center',
        text: '一个学生多次考试成绩分布'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['学生成绩']
      },
      grid: {
        left: '1%',
        right: '5%',
        bottom: '3%',
        containLabel: true,
        y2: 140
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        name: '考次',
        boundaryGap: false,
        data: examName,
        axisLabel: {
          interval: 0,//横轴信息全部显示
          rotate: -30,//-30度角倾斜显示
        }
      },
      yAxis: {
        type: 'log',
        name: '分数'
      },
      series: [
        {
          name: '学生成绩',
          type: 'line',
          stack: '分数',
          data: scoreRatio
        }
      ]
    };

    return option;
  }

  getDataAnalysisOption2(data) {
    let realName = [];
    let average = [];
    for (let entry of data) {
      realName.push(entry.realName);
      average.push(entry.average);
    }

    let option = {
      title: {
        left: 'center',
        text: '班级多次考试学生成绩分布'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['平均分']
      },
      grid: {
        left: '1%',
        right: '5%',
        bottom: '3%',
        containLabel: true,
        y2: 140
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        name: '姓名',
        boundaryGap: false,
        data: realName,
        axisLabel: {
          interval: 0,//横轴信息全部显示
          rotate: -30,//-30度角倾斜显示
        }
      },
      yAxis: {
        type: 'log',
        name: '平均分'
      },
      series: [
        {
          name: '平均分',
          type: 'line',
          stack: '分数',
          data: average
        }
      ]
    };

    return option;


    // let option2 = {
    //   title: {
    //     text: '对数轴示例',
    //     left: 'center'
    //   },
    //   tooltip: {
    //     trigger: 'item',
    //     formatter: '{a} <br/>{b} : {c}'
    //   },
    //   legend: {
    //     left: 'left',
    //     data: ['2的指数', '3的指数']
    //   },
    //   xAxis: {
    //     type: 'category',
    //     name: 'x',
    //     splitLine: {show: false},
    //     data: ['一', '二', '三', '四', '五', '六', '七', '八', '九']
    //   },
    //   grid: {
    //     left: '3%',
    //     right: '4%',
    //     bottom: '3%',
    //     containLabel: true
    //   },
    //   yAxis: {
    //     type: 'log',
    //     name: 'y'
    //   },
    //   series: [
    //     {
    //       name: '3的指数',
    //       type: 'line',
    //       data: [1, 33, 24, 27, 81, 247, 741, 2223, 6669]
    //     },
    //     {
    //       name: '2的指数',
    //       type: 'line',
    //       data: [1, 2, 4, 50, 16, 32, 64, 128, 256]
    //     },
    //
    //   ]
    // };
    // return option2;
  }


}
