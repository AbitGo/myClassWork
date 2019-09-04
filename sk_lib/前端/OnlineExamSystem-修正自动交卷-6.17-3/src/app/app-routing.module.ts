import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {ErrorPageComponent} from './components/error-page/error-page.component';
import {MainComponent} from "./components/main/main.component";
import {MainsComponent} from "./components/mains/mains.component";
import {DepartmentManagementComponent} from "./components/information-manage/department-manage/department-manage.component";
import {SchoolManageComponent} from "./components/information-manage/school-manage/school-manage.component";
import {AdminManageComponent} from "./components/information-manage/admin-manage/admin-manage.component";
import {LeadershipManageComponent} from "./components/information-manage/leadership-manage/leadership-manage.component";
import {StudentManageComponent} from "./components/information-manage/student-manage/student-manage.component";
import {TeacherManageComponent} from "./components/information-manage/teacher-manage/teacher-manage.component";
import {CourseManageComponent} from "./components/information-manage/course-manage/course-manage.component";
import {ClassManageComponent} from "./components/information-manage/class-manage/class-manage.component";
import {MajorManageComponent} from "./components/information-manage/major-manage/major-manage.component";
import {DesignPaperComponent} from './components/exam-design/design-paper/design-paper.component';
import {GetAnalysisComponent} from "./components/data-analysis/get-analysis/get-analysis.component";
import {KnowledgeAnalysisComponent} from "./components/data-analysis/knowledge-analysis/knowledge-analysis.component";
import {ScoreAnalysisComponent} from "./components/data-analysis/score-analysis/score-analysis.component";
import {UploadTitleComponent} from "./components/title-manage/upload-title/upload-title.component";
import {GradeQueryComponent} from "./components/information-query/grade-query/grade-query.component";
import {StudentQueryComponent} from "./components/information-query/student-query/student-query.component";
import {TeacherQueryComponent} from "./components/information-query/teacher-query/teacher-query.component";
import {KnowledageManageComponent} from "./components/title-manage/knowledage-manage/knowledage-manage.component";
import {TitleListComponent} from "./components/title-manage/title-list/title-list.component";
import {PaperHistoryComponent} from "./components/exam-design/paper-history/paper-history.component";
import {ExamPageComponent} from "./components/exam-page/exam-page.component";
import {InsertExamComponent} from "./components/exam-design/insert-exam/insert-exam.component";
import {SubjectBindComponent} from "./components/information-manage/subject-bind/subject-bind.component";
import {ImportTitleComponent} from "./components/title-manage/import-title/import-title.component";
import {ErrorTitleComponent} from "./components/title-manage/error-title/error-title.component";
import {TitleAnalysisComponent} from "./components/data-analysis/title-analysis/title-analysis.component";
import {ExportDataComponent} from "./components/report/export-data/export-data.component";
import {PlanJobComponent} from "./components/title-manage/plan-job/plan-job.component";
import {TitlePracticeComponent} from "./components/title-practice/title-practice.component";
import {ChangePasswordComponent} from "./components/information-manage/change-password/change-password.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {
    path: 'main', component: MainComponent, children: [
    {path: '', component: MainsComponent},
    //信息查询
    {path: 'grade-query', component: GradeQueryComponent},
    {path: 'student-query', component: StudentQueryComponent},
    {path: 'teacher-query', component: TeacherQueryComponent},

    //信息管理
    {path: 'admin-manage', component: AdminManageComponent},
    {path: 'leadership-manage', component: LeadershipManageComponent},
    {path: 'school-manage', component: SchoolManageComponent},
    {path: 'department-manage', component: DepartmentManagementComponent},
    {path: 'major-manage', component: MajorManageComponent},
    {path: 'class-manage', component: ClassManageComponent},
    {path: 'course-manage', component: CourseManageComponent},
    {path: 'teacher-manage', component: TeacherManageComponent},
    {path: 'student-manage', component: StudentManageComponent},
    {path: 'subject-bind', component: SubjectBindComponent},
    {path: 'change-password', component: ChangePasswordComponent},
    //题目管理
    {path: 'error-title', component: ErrorTitleComponent},
    {path: 'upload-title', component: UploadTitleComponent},
    {path: 'title-list', component: TitleListComponent},
    {path: 'import-title', component: ImportTitleComponent},
    {path: 'plan-job', component: PlanJobComponent},
    {path: 'knowledage-manage', component: KnowledageManageComponent},


    //考试设计
    {path: 'design-paper', component: DesignPaperComponent},
    {path: 'insert-exam', component: InsertExamComponent},
    {path: 'paper-history', component: PaperHistoryComponent},
    //数据分析
    {path: 'get-analysis', component: GetAnalysisComponent},
    {path: 'knowledge-analysis', component: KnowledgeAnalysisComponent},
    {path: 'title-analysis', component: TitleAnalysisComponent},
    {path: 'score-analysis', component: ScoreAnalysisComponent},
    //报表
    {path: 'export-data', component: ExportDataComponent},

  ]
  },
  // 考试
  {path: 'exam', component: ExamPageComponent},
  {path: 'title-practice', component: TitlePracticeComponent},
  {path: '**', component: ErrorPageComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
