import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICourseCreate } from 'src/app/course/interfaces/ICourseCreate';
import { ICourseEdit } from 'src/app/course/interfaces/ICourseEdit';
import ICourseItem from 'src/app/course/interfaces/ICourseItem';
import { environment } from 'src/environments/environment';
import IStreamCourse from 'src/app/course/interfaces/IStreamCourse';
import { ICourseDetails} from 'src/app/course/interfaces/ICourseDetails';
import{IEmployeeCourse, IEmployeeCoursesList} from '../model'
import { ToastrService } from 'ngx-toastr';

const ADMIN_ALL_COURSES = "/courses";
const ADMIN_CREATE_COURSE = "/courses/create";
const ADD_PICTURE_TO_COURSE ="/courses/create/picture";
const ADMIN_DELETE_COURSE = "/courses/delete/";
const ADMIN_UPDATE_COURSE = "/courses/edit/";
const BUSINESS_OWNER_SEARCH_COURSE = "/courses/search";
const BUSINESS_OWNER_COURSES = "/courses/company/catalog";
const BUSINESS_OWNER_ADD_COURSE = "/courses/add/";
const BUSINESS_OWNER_REMOVE_COURSE = "/courses/remove/";
const COURSE_DETAILS = "/courses/details/";
const EMPLOYEE_COURSES = "/courses/get-by-employee";
const CHANGE_EMPLOYEE_COURSE_STATUS = '/courses/change/status/';
const COURSE_STREAM = '/courses/lectures/';
const RANDOM_COURSES = '/courses/employee/dashboard';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getEmployeeRandomCourses(): Observable<Array<IEmployeeCourse>> {
    return this.http.get<Array<IEmployeeCourse>>(environment.apiUrlCourses + RANDOM_COURSES);
  }


  getAllAdminCourse() {

    return this.http.get<Array<ICourseItem>>(environment.apiUrlCourses + ADMIN_ALL_COURSES);

  }

  createCourse(data, payload): any {

    return this.http.post(environment.apiUrlCourses + ADMIN_CREATE_COURSE, data, {observe: 'response', responseType: 'text'})
    .subscribe( (result)=> {
      console.log(result);
      if(result.status === 201){
        const courseId =result.body;
        payload.append('id', courseId);
        this.http.put(environment.apiUrlCourses + ADD_PICTURE_TO_COURSE, payload, {observe: 'response'})
        .subscribe((data) => {
          this.toastr.success('The course has been created successfully!', 'Create course');
        });
      }
    }
    ,(error)=>{console.log(error);}
    );
  }

  deleteCourse(id: string) {

    return this.http.delete(environment.apiUrlCourses + ADMIN_DELETE_COURSE + id);

  }

  getCourseById(id: string):Observable<ICourseEdit> {
    return this.http.get<ICourseEdit>(environment.apiUrlCourses + ADMIN_UPDATE_COURSE + id);
  }

   editCourse(data, payload, id): any {

    return this.http.post(environment.apiUrlCourses + ADMIN_UPDATE_COURSE + id, data, {observe: 'response', responseType: 'text'})
    .subscribe( (result)=> {
      if(result.status === 201){
        console.log(result.status);

        payload.append('id', id);
        this.http.put(environment.apiUrlCourses + ADD_PICTURE_TO_COURSE, payload, {observe: 'response'})
        .subscribe((data) => {
          this.toastr.success('The course has been edited successfully!', 'Edit course');
        });
      }
    },(error)=>{console.log(error);}
    );
  }

  searchCourses(searchParam) {

    return this.http.post<Array<ICourseItem>>(environment.apiUrlCourses+ BUSINESS_OWNER_SEARCH_COURSE, searchParam);
  }

  getCoursesByBusinessOwner() {

    return this.http.get<Array<ICourseItem>>(environment.apiUrlCourses + BUSINESS_OWNER_COURSES);
  }

  addCourseToBusinessOwner(id: string) {

    return this.http.post<string>(environment.apiUrlCourses + BUSINESS_OWNER_ADD_COURSE + id, id);
  }

  removeCourseFromBusinessOwnerList(id: string){
    return this.http.post<string>(environment.apiUrlCourses + BUSINESS_OWNER_REMOVE_COURSE + id, id);
  }

  getCourseDetailsById(id: string):Observable<ICourseDetails>{

    return this.http.get<ICourseDetails>(environment.apiUrlCourses + COURSE_DETAILS + id);
  }

  getCoursesByEmployee():Observable<IEmployeeCoursesList>{
    return this.http.get<IEmployeeCoursesList>(environment.apiUrlCourses+EMPLOYEE_COURSES);
  }

  changeEmployeeCourseStatus(courseId:string):Observable<any>{
     return this.http.get<any>(environment.apiUrlCourses+CHANGE_EMPLOYEE_COURSE_STATUS+courseId);
  }

  getStreamCoursebyId(id: string): Observable<IStreamCourse> {
    return this.http.get<IStreamCourse>(environment.apiUrlCourses + COURSE_STREAM + id);
  }

}
