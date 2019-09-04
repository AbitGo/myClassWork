import {Component, OnInit} from '@angular/core';
import {DataAnalysisServiceService} from '../../../serve/data-analysis-service.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {InsertExamService} from '../../../serve/exam-design/insert-exam.service';

@Component({
  selector: 'app-knowledge-analysis',
  templateUrl: './knowledge-analysis.component.html',
  styleUrls: ['./knowledge-analysis.component.css']
})
export class KnowledgeAnalysisComponent implements OnInit {

  //下拉框数据
  _department;
  departments;
  _major;
  majors;
  _class;
  classes;
  _student;
  students;
  _knowledge;
  knowledges;
  _exam;
  exams;

  option;
  option1;
  option2;
  option3;
  option4;

  constructor(private dataAnalysisService: DataAnalysisServiceService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private knowledageManageServerService: KnowledageManageServiceService,
              private insertExamService: InsertExamService,
              private classManageServerService: ClassManageServerService,) {
  }

  ngOnInit() {
    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });

    this.knowledageManageServerService.getKnowledage({
      'subjectId': sessionStorage.getItem('selectedSubject')
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this.knowledges = data.data.list;
      }
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

    //一个学生一次考试知识点分析情况
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('studentId', this._student);
    urlSearchParams.append('examId', this._exam);
    let param = urlSearchParams.toString();
    this.dataAnalysisService.showAnalysisFromStudentKnowledgePointTest(param, data => {
      // if (data.success) {
      console.log('一个学生一次考试知识点分析情况');
      console.log(data);
      this.option1 = this.getDataAnalysisOption4(data.data);
    });

    //一个学生历史考试知识点得分情况
    let urlSearchParams5 = new URLSearchParams();
    urlSearchParams5.append('studentId', this._student);
    urlSearchParams5.append('subjectId', sessionStorage.getItem('selectedSubject'));
    let param5 = urlSearchParams5.toString();
    this.dataAnalysisService.showAnalysisFromStudentKnowledgePointHistoryTest(param5, data => {
      // if (data.success) {
      console.log('一个学生历史考试知识点得分情况');
      console.log(data);
      this.option2 = this.getDataAnalysisOption5(data.data);
    });

    //一次考试一个班级知识点分布情况
    let urlSearchParams7 = new URLSearchParams();
    urlSearchParams7.append('examId', this._exam);
    urlSearchParams7.append('classId', this._class);
    let param7 = urlSearchParams7.toString();
    this.dataAnalysisService.showAnalysisFromClassKnowledgePointTest(param7, data => {
      // if (data.success) {
      console.log('一次考试一个班级知识点分布情况');
      console.log(data);
      this.option3 = this.getDataAnalysisOption7(data.data);
    });

    //多个个知识点历史得分情况，取错误率前10
    let urlSearchParams8 = new URLSearchParams();
    urlSearchParams8.append('subjectId', sessionStorage.getItem('selectedSubject'));
    let param8 = urlSearchParams8.toString();
    this.dataAnalysisService.showAnalysisFromKnowledgePointHistoryResult(param8, data => {
      // if (data.success) {
      console.log('多个个知识点历史得分情况，取错误率前10');
      console.log(data);
      this.option4 = this.getDataAnalysisOption8(data.data);
    });

  }

  getDataAnalysisOption4(data) {
    let errorRatio = [];
    let knowleagePointName = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      knowleagePointName.push(entry.knowleagePointName);
    }

    let option = {
      title: {
        left: 'center',
        text: '一个学生一次考试知识点分析情况'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['错误率']
      },
      grid: {
        left: '3%',
        right: '10%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        name: '知识点',
        boundaryGap: false,
        data: knowleagePointName
      },
      yAxis: {
        type: 'log',
        name: '错误率'
      },
      series: [
        {
          name: '错误率',
          type: 'line',
          stack: '错误率',
          data: errorRatio
        }
      ]
    };
    return option;
  }

  getDataAnalysisOption5(data) {
    let errorRatio = [];
    let knowleagePointName = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      knowleagePointName.push(entry.knowleagePointName);
    }

    let option = {
      title: {
        left: 'center',
        text: '一个学生历史考试知识点得分情况'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['错误率']
      },
      grid: {
        left: '3%',
        right: '10%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        name: '知识点',
        boundaryGap: false,
        data: knowleagePointName
      },
      yAxis: {
        type: 'log',
        name: '错误率'
      },
      series: [
        {
          name: '错误率',
          type: 'line',
          stack: '错误率',
          data: errorRatio
        }
      ]
    };
    return option;
  }

  getDataAnalysisOption7(data) {
    let errorRatio = [];
    let knowleagePointName = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      knowleagePointName.push(entry.knowleagePointName);
    }

    let option = {
      title: {
        left: 'center',
        text: '一次考试一个班级知识点分布情况'
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['错误率']
      },
      grid: {
        left: '3%',
        right: '10%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        name: '知识点',
        boundaryGap: false,
        data: knowleagePointName
      },
      yAxis: {
        type: 'log',
        name: '错误率'
      },
      series: [
        {
          name: '错误率',
          type: 'line',
          stack: '错误率',
          data: errorRatio
        }
      ]
    };
    return option;
  }

  getDataAnalysisOption8(data) {
    let errorRatio = [];
    let knowleagePointName = [];
    let seriesData = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      knowleagePointName.push(entry.knowleagePointName);
      seriesData.push({
        name: entry.knowleagePointName,
        // value: Math.round(Math.random() * 100000)
        value: entry.errorRatio
      });
    }

    let option = {
      title: {
        text: '多个个知识点历史得分情况',
        subtext: '知识点错误率',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: knowleagePointName
      },
      series: [
        {
          name: '知识点名称',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
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

    // let option = {
    //   title: {
    //     left: 'center',
    //     text: '多个个知识点历史得分情况，取错误率前10'
    //   },
    //   tooltip: {
    //     trigger: 'axis',
    //     formatter: '{a} <br/>{b} : {c}'
    //   },
    //   legend: {
    //     left: 'left',
    //     data: ['错误率']
    //   },
    //   grid: {
    //     left: '3%',
    //     right: '4%',
    //     bottom: '3%',
    //     containLabel: true
    //   },
    //   toolbox: {
    //     feature: {
    //       saveAsImage: {}
    //     }
    //   },
    //   xAxis: {
    //     type: 'category',
    //     name: '知识点',
    //     boundaryGap: false,
    //     data: knowleagePointName
    //   },
    //   yAxis: {
    //     type: 'log',
    //     name: '错误率'
    //   },
    //   series: [
    //     {
    //       name: '错误率',
    //       type: 'line',
    //       stack: '错误率',
    //       data: errorRatio
    //     }
    //   ]
    // };
    return option;
  }


  test() {
    this.option1 = {
      title: {
        text: '成绩分布',
        left: 'left'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      toolbox: {
        feature: {
          dataView: {show: true, readOnly: false},
          magicType: {show: true, type: ['line', 'bar']},
          restore: {show: true},
          saveAsImage: {show: true}
        }
      },
      legend: {
        data: ['大二上', '大二下', '大三上']
      },
      xAxis: [
        {
          type: 'category',
          data: ['不及格', '及格', '中等', '良好', '优秀'],
          axisPointer: {
            type: 'shadow'
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '分数',
          min: 0,
          max: 100,
          interval: 10,
          axisLabel: {
            formatter: '{value} 分'
          }
        },
        {
          type: 'value',
          name: '人数',
          min: 0,
          max: 25,
          interval: 5,
          axisLabel: {
            formatter: '{value} 人'
          }
        }
      ],
      series: [
        {
          name: '大二上',
          type: 'bar',
          data: [2.0, 4.9, 7.0, 23.2, 25.6,]
        },
        {
          name: '大二下',
          type: 'bar',
          data: [2.6, 5.9, 9.0, 26.4, 28.7,]
        },
        {
          name: '大三上',
          type: 'bar',
          yAxisIndex: 1,
          data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2,]
        }
      ]
    };
    this.option2 = {
      title: {
        text: '班级科目均衡性'
      },
      tooltip: {},
      legend: {
        data: ['年级平均', '班级平均']
      },
      radar: {
        // shape: 'circle',
        name: {
          textStyle: {
            color: '#fff',
            backgroundColor: '#999',
            borderRadius: 3,
            padding: [3, 5]
          }
        },
        indicator: [
          {name: '语文', max: 100},
          {name: '数学', max: 100},
          {name: '英语', max: 100},
          {name: '物理', max: 100},
          {name: '化学', max: 100},
          {name: '政治', max: 100},
          {name: '历史', max: 100},
          {name: '地理', max: 100},
        ]
      },
      series: [{
        name: '年级平均 vs 班级平均',
        type: 'radar',
        // areaStyle: {normal: {}},
        data: [
          {
            value: [70, 68, 60, 75, 80, 81, 72, 91],
            name: '年级平均'
          },
          {
            value: [77, 88, 99, 79, 96, 85, 90, 93],
            name: '班级平均'
          }
        ]
      }]
    };
    this.option3 = {
      title: {
        text: '班级一次考试学生成绩分布情况',
        subtext: '考试',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['不及格', '及格', '中等', '良好', '优秀']
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            {value: 10, name: '不及格'},
            {value: 40, name: '及格'},
            {value: 30, name: '中等'},
            {value: 20, name: '良好'},
            {value: 10, name: '优秀'}
          ],
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
    this.option4 = {
      title: {
        text: '对数轴示例',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['2的指数', '3的指数']
      },
      xAxis: {
        type: 'category',
        name: 'x',
        splitLine: {show: false},
        data: ['一', '二', '三', '四', '五', '六', '七', '八', '九']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      yAxis: {
        type: 'log',
        name: 'y'
      },
      series: [
        {
          name: '3的指数',
          type: 'line',
          data: [1, 33, 24, 27, 81, 247, 741, 2223, 6669]
        },
        {
          name: '2的指数',
          type: 'line',
          data: [1, 2, 4, 50, 16, 32, 64, 128, 256]
        },

      ]
    };
  }


}
