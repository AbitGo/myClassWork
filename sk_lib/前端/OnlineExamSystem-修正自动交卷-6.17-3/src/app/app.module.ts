import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {LoginServeService} from "./serve/login-serve.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MainComponent} from './components/main/main.component';
import {MainsComponent} from './components/mains/mains.component';
import {DepartmentManagementComponent} from './components/information-manage/department-manage/department-manage.component';
import {SchoolManageComponent} from './components/information-manage/school-manage/school-manage.component';
import {AdminManageComponent} from './components/information-manage/admin-manage/admin-manage.component';
import {ClassManageComponent} from './components/information-manage/class-manage/class-manage.component';
import {CourseManageComponent} from './components/information-manage/course-manage/course-manage.component';
import {LeadershipManageComponent} from './components/information-manage/leadership-manage/leadership-manage.component';
import {MajorManageComponent} from './components/information-manage/major-manage/major-manage.component';
import {StudentManageComponent} from './components/information-manage/student-manage/student-manage.component';
import {TeacherManageComponent} from './components/information-manage/teacher-manage/teacher-manage.component';
import {TableServiceService} from "./serve/table-service.service";
import {DesignPaperComponent} from './components/exam-design/design-paper/design-paper.component';
import {GetAnalysisComponent} from './components/data-analysis/get-analysis/get-analysis.component';
import {NgxEchartsModule} from "ngx-echarts";
import {KnowledgeAnalysisComponent} from './components/data-analysis/knowledge-analysis/knowledge-analysis.component';
import {ScoreAnalysisComponent} from './components/data-analysis/score-analysis/score-analysis.component';
import {DataAnalysisServiceService} from "./serve/data-analysis-service.service";
import {UploadTitleComponent} from './components/title-manage/upload-title/upload-title.component';
import {UMeditorModule} from "ngx-umeditor";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {GradeQueryComponent} from './components/information-query/grade-query/grade-query.component';
import {StudentQueryComponent} from './components/information-query/student-query/student-query.component';
import {TeacherQueryComponent} from './components/information-query/teacher-query/teacher-query.component';
import {GradeQueryServerService} from "./serve/information-query/grade-query-server.service";
import {AdminManageServerService} from "./serve/information-manage/admin-manage-server.service";
import {LeadershipManageServerService} from "./serve/information-manage/leadership-manage-server.service";
import {SchoolManageServerService} from "./serve/information-manage/school-manage-server.service";
import {DepartmentManageServerService} from "./serve/information-manage/department-manage-server.service";
import {MajorManageServerService} from "./serve/information-manage/major-manage-server.service";
import {CourseManageServerService} from "./serve/information-manage/course-manage-server.service";
import {ClassManageServerService} from "./serve/information-manage/class-manage-server.service";
import {TeacherManageServerService} from "./serve/information-manage/teacher-manage-server.service";
import {StudentManageServerService} from "./serve/information-manage/student-manage-server.service";
import {KnowledageManageServiceService} from "./serve/title-manage/knowledage-manage-service.service";
import {KnowledageManageComponent} from './components/title-manage/knowledage-manage/knowledage-manage.component';
import {Ng2Webstorage} from "ngx-webstorage";
import {UtilsServiceService} from "./serve/utils-service.service";
import {TitleManageServerService} from "./serve/title-manage/title-manage-server.service";
import {TitleListComponent} from './components/title-manage/title-list/title-list.component';
import {KatexModule} from "ng-katex/src/ng-katex.module";
import {DesignPaperService} from "./serve/exam-design/design-paper.service";
import {ExamHistoryComponent} from './components/exam-design/exam-history/exam-history.component';
import {PaperHistoryComponent} from './components/exam-design/paper-history/paper-history.component';
import {PaperDetailComponent} from './components/exam-design/paper-detail/paper-detail.component';
import {TestService} from './serve/test/test.service';
import {ExamPageComponent} from "./components/exam-page/exam-page.component";
import {InsertExamComponent} from './components/exam-design/insert-exam/insert-exam.component';
import {InsertExamService} from "./serve/exam-design/insert-exam.service";
import {SubjectBindComponent} from './components/information-manage/subject-bind/subject-bind.component';
import {ExamPageServiceService} from "./serve/exam-page/exam-page-service.service";
import {ImportTitleComponent} from './components/title-manage/import-title/import-title.component';
import {FileUploadModule} from "ng2-file-upload";
import {ImportTitleService} from "./serve/file-upload/import-title.service";
import {ErrorTitleService} from "./serve/title-manage/error-title.service";
import {TitleAnalysisComponent} from './components/data-analysis/title-analysis/title-analysis.component';
import {MessageServiceService} from "./serve/message-service.service";
import {ExportDataComponent} from './components/report/export-data/export-data.component';
import {ExportDataServerService} from "./serve/report/export-data-server.service";
import {TimeDataPipe} from './pipe/time-data.pipe';
import {PlanJobComponent} from './components/title-manage/plan-job/plan-job.component';
import {PlanJobServerService} from "./serve/title-manage/plan-job-server.service";
import { TitlePracticeComponent } from './components/title-practice/title-practice.component';
import { ChangePasswordComponent } from './components/information-manage/change-password/change-password.component';
import {ErrorTitleComponent} from "./components/title-manage/error-title/error-title.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ErrorPageComponent,
    MainComponent,
    MainsComponent,
    DepartmentManagementComponent,
    SchoolManageComponent,
    AdminManageComponent,
    ClassManageComponent,
    CourseManageComponent,
    LeadershipManageComponent,
    MajorManageComponent,
    StudentManageComponent,
    TeacherManageComponent,
    DesignPaperComponent,
    GetAnalysisComponent,
    KnowledgeAnalysisComponent,
    ScoreAnalysisComponent,
    UploadTitleComponent,
    GradeQueryComponent,
    StudentQueryComponent,
    TeacherQueryComponent,
    KnowledageManageComponent,
    TitleListComponent,
    ExamHistoryComponent,
    PaperHistoryComponent,
    PaperDetailComponent,
    ExamPageComponent,
    InsertExamComponent,
    SubjectBindComponent,
    ImportTitleComponent,
    ErrorTitleComponent,
    TitleAnalysisComponent,
    ExportDataComponent,
    TimeDataPipe,
    PlanJobComponent,
    TitlePracticeComponent,
    ChangePasswordComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule.forRoot(),
    NgxEchartsModule,
    UMeditorModule,
    NgbModule,
    Ng2Webstorage,
    KatexModule,
    FileUploadModule

  ],
  providers: [
    LoginServeService,
    TableServiceService,
    DataAnalysisServiceService,
    GradeQueryServerService,
    AdminManageServerService,
    LeadershipManageServerService,
    SchoolManageServerService,
    DepartmentManageServerService,
    MajorManageServerService,
    CourseManageServerService,
    ClassManageServerService,
    TeacherManageServerService,
    StudentManageServerService,
    KnowledageManageServiceService,
    UtilsServiceService,
    TitleManageServerService,
    DesignPaperService,
    TestService,
    InsertExamService,
    ExamPageServiceService,
    ImportTitleService,
    ErrorTitleService,
    MessageServiceService,
    ExportDataServerService,
    PlanJobServerService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
