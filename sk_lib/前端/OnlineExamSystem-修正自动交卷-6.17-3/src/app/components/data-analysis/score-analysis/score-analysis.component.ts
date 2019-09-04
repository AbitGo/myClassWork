import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {DataAnalysisServiceService} from '../../../serve/data-analysis-service.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {InsertExamService} from '../../../serve/exam-design/insert-exam.service';

@Component({
  selector: 'app-score-analysis',
  templateUrl: './score-analysis.component.html',
  styleUrls: ['./score-analysis.component.css']
})
export class ScoreAnalysisComponent implements OnInit {
  data;

  _department;
  departments;
  _major;
  majors;
  _class;
  classes;
  _exam;
  exams;

  _startTime = null;
  _endTime = null;

  option;
  option1;
  option2;
  option3;
  option4;

  constructor(private tableServiceService: TableServiceService,
              private dataAnalysisService: DataAnalysisServiceService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private insertExamService: InsertExamService,
              private classManageServerService: ClassManageServerService,) {

  }

  ngOnInit() {

    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });

    this.insertExamService.getExam({
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this.exams = data.data.list;

    });

  }

  //教师根据学院查询专业
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

  //教师根据学院和专业查询班级
  queryClass(major) {
    this.classManageServerService.getClassFromTeacher({
      // 'page': 1,
      // 'size': 10,
      'departmentId': this._department,
      'majorId': major,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.classes = data.data.list;
    });
  }

  submit() {

    //班级一次考试学生成绩分布
    let time1 = this.changeTime(new Date(this._startTime));
    let time2 = this.changeTime(new Date(this._endTime));
    let urlSearchParams2 = new URLSearchParams();
    urlSearchParams2.append('classId', this._class);
    urlSearchParams2.append('examId', this._exam);
    urlSearchParams2.append('startTime', time1);
    urlSearchParams2.append('endTime', time2);
    let param2 = urlSearchParams2.toString();
    this.dataAnalysisService.showAnalysisFromClassStudentsTest(param2, data => {
      // if (data.success) {
      console.log('班级一次考试学生成绩分布');
      console.log(data);
      this.option1 = this.getDataAnalysisOption2(data.data);
    });


    //多次考试整个班级分数情况
    let urlSearchParams9 = new URLSearchParams();
    urlSearchParams9.append('classId', this._class);
    urlSearchParams9.append('startTime', time1);
    urlSearchParams9.append('endTime', time2);
    urlSearchParams9.append('subjectId', sessionStorage.getItem('selectedSubject'));
    let param9 = urlSearchParams9.toString();
    this.dataAnalysisService.showAnalysisFromClassManyTestResult(param9, data => {
      // if (data.success) {
      console.log('多次考试整个班级分数情况');
      console.log(data);
      this.option2 = this.getDataAnalysisOption9(data.data);
    });

  }

  getDataAnalysisOption2(data) {
    let realName = [];
    let scoreRatio = [];
    let seriesData = [];
    for (let entry of data) {
      realName.push(entry.realName);
      scoreRatio.push(entry.scoreRatio);
      seriesData.push({
        name: entry.realName,
        // value: Math.round(Math.random() * 100000)
        value: entry.scoreRatio
      });
    }

    let option = {
      title: {
        text: '学生成绩',
        subtext: '科目成绩',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        type: 'scroll',
        orient: 'vertical',
        right: 10,
        top: 20,
        bottom: 20,
        data: realName
      },
      series: [
        {
          name: '姓名',
          type: 'pie',
          radius: '55%',
          center: ['40%', '50%'],
          data: seriesData,
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
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

  getDataAnalysisOption9(data) {

    let examName = [];
    let averageRatio = [];
    for (let entry of data) {
      if (entry.examName != "") {
        examName.push(entry.examName);
        averageRatio.push(entry.averageRatio);
      }
    }
    console.log(examName + '->' + averageRatio);

    let option = {
      title: {
        left: 'center',
        text: '多次考试整个班级分数情况'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['考次']
      },
      grid: {
        left: '1%',
        right: '5%',
        bottom: '3%',
        containLabel: true,
        y2: 140,
        x2: 140
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
        name: '平均错误率'
      },
      series: [
        {
          name: '考试错误率',
          type: 'line',
          stack: '错误率',
          data: averageRatio
        }
      ]
    };

    return option;
  }


  //格式化时间为2018-10-8格式
  changeTime(time) {
    let year = time.getFullYear() < 10 ? ('0' + time.getFullYear()) : time.getFullYear();
    let month = (time.getMonth() + 1) < 10 ? ('0' + (time.getMonth() + 1)) : (time.getMonth() + 1);
    let day = time.getDate() < 10 ? ('0' + time.getDate()) : time.getDate();
    return year + '-' + month + '-' + day;
  }

  searchScoreAnalysis() {
    this.option = this.tableServiceService.genData(50);
  }


}
