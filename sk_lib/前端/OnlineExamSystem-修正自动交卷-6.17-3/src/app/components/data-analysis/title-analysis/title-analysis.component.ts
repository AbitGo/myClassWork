import {Component, OnInit} from '@angular/core';
import {DataAnalysisServiceService} from '../../../serve/data-analysis-service.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {InsertExamService} from '../../../serve/exam-design/insert-exam.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {TitleManageServerService} from '../../../serve/title-manage/title-manage-server.service';

@Component({
  selector: 'app-title-analysis',
  templateUrl: './title-analysis.component.html',
  styleUrls: ['./title-analysis.component.css']
})
export class TitleAnalysisComponent implements OnInit {

  _exam;
  exams;
  _knowledge;
  knowledges;
  _title;
  titles;

  option;
  option1;
  option2;
  option3;
  option4;

  constructor(private dataAnalysisService: DataAnalysisServiceService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private titleManageServerService: TitleManageServerService,
              private knowledageManageServerService: KnowledageManageServiceService,
              private insertExamService: InsertExamService,
              private classManageServerService: ClassManageServerService,) {
  }

  ngOnInit() {

    this.insertExamService.getExam({
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this.exams = data.data.list;

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
  }

  queryQuestion(knowledge) {
    this.titleManageServerService.getTitle({
      'subjectId': sessionStorage.getItem('selectedSubject'),
      'knowleagePointId': knowledge
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this.titles = data.data.list;
      }
    });
  }

  submit() {

    //一次考试所有题目情况
    let urlSearchParams10 = new URLSearchParams();
    urlSearchParams10.append('examId', this._exam);
    let param10 = urlSearchParams10.toString();
    this.dataAnalysisService.showAnalysisFromTestAllTitle(param10, data => {
      // if (data.success) {
      console.log('一次考试所有题目情况');
      console.log(data);
      this.option2 = this.getDataAnalysisOption10(data.data);
    });

    //一个题目历史得分情况
    let urlSearchParams11 = new URLSearchParams();
    urlSearchParams11.append('questionId', this._title);
    let param11 = urlSearchParams11.toString();
    this.dataAnalysisService.showAnalysisFromTitleHistoryResult(param11, data => {
      // if (data.success) {
      console.log('一个题目历史得分情况');
      console.log(data);
      this.option1 = this.getDataAnalysisOption11(data.data);
    });

  }

  getDataAnalysisOption10(data) {
    let errorRatio = [];
    let questionName = [];
    let seriesData = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      questionName.push(entry.questionName);
      seriesData.push({
        name: entry.questionName,
        // value: Math.round(Math.random() * 100000)
        value: entry.errorRatio
      });
    }

    let option = {
      title: {
        text: '一次考试所有题目情况',
        subtext: '来源考试',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        x: 'left',
        data: questionName
      },
      toolbox: {
        show: true,
        feature: {
          mark: {show: true},
          dataView: {show: true, readOnly: false},
          magicType: {
            show: true,
            type: ['pie', 'funnel'],
            option: {
              funnel: {
                x: '25%',
                width: '50%',
                funnelAlign: 'left',
                max: 1548
              }
            }
          },
          restore: {show: true},
          saveAsImage: {show: true}
        }
      },
      calculable: true,
      series: [
        {
          name: '错误率',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: seriesData
        }
      ]
    };


    // let option = {
    //   title: {
    //     left: 'center',
    //     text: '一次考试所有题目情况'
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
    //     name: '题目',
    //     boundaryGap: false,
    //     data: questionName
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

  getDataAnalysisOption11(data) {
    let errorRatio = [];
    let examId = [];
    for (let entry of data) {
      errorRatio.push(entry.errorRatio);
      examId.push(entry.examName);
    }

    let option = {
      title: {
        left: 'center',
        text: '一个题目历史得分情况'
      },
      tooltip: {
        trigger: 'axis',
        // formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['错误率']
      },
      grid: {
        left: '3%',
        right: '6%',
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
        data: examId,
        axisLabel: {
          interval: 0,//横轴信息全部显示
          rotate: -30,//-30度角倾斜显示
        }
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

}
