import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseService } from 'src/app/core/service';
import { ICategoryItem } from '../interfaces/ICategoryItem';
import ICourseItem from '../interfaces/ICourseItem';
import { ILanguageItem } from '../interfaces/ILanguageItem';
import { ISearchParams } from '../interfaces/ISearchParams';

@Component({
  selector: 'app-course-catalog',
  templateUrl: './course-catalog.component.html',
  styleUrls: ['./course-catalog.component.scss']
})
export class CourseCatalogComponent implements OnInit {

  allCourses$: Observable<Array<ICourseItem>>;
  selectedItemsList= [];
  checkedIDs: Array<ILanguageItem> = [];

  selectedItemsListCategories= [];
  checkedIDsCategories: Array<ICategoryItem> = [];

  searchParams: ISearchParams = <ISearchParams>{};

  loading=true;
  interval;
  
  checkboxesDataList = [
    {
      id: 'english',
      label: 'English',
      isChecked: false
    },
    {
      id: 'french',
      label: 'French',
      isChecked: false
    },
    {
      id: 'german',
      label: 'German',
      isChecked: false
    },
    {
      id: 'spanish',
      label: 'Spanish',
      isChecked: false
    },
  ]

  checkboxesDataListCategories = [
    {
      id: 'art',
      label: 'Art',
      isChecked: false
    },
    {
      id: 'design',
      label: 'Design',
      isChecked: false
    },
    {
      id: 'marketing',
      label: 'Marketing',
      isChecked: false
    },
    {
      id: 'leadership',
      label: 'Leadership',
      isChecked: false
    },
    {
      id: 'personal_development',
      label: 'Personal development',
      isChecked: false
    },
    {
      id: 'data_science',
      label: 'Data science',
      isChecked: false
    },
    {
      id: 'computer_science',
      label: 'Computer science',
      isChecked: false
    }
  ]

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
    if (this.loading) {
      this.interval = setTimeout(() => {
        this.allCourses$ = this.courseService.getAllAdminCourse();
        this.loading = false;
      }, 1000);
    }   
   this.fetchSelectedItems();
   this.fetchCheckedIDs();
  }

  changeSelection() {
    this.fetchSelectedItems()
    this.fetchSelectedItemsCategories();
    this.fetchCheckedIDs();
    this.fetchCheckedIDsCategories();

    this.searchParams = {categories: this.checkedIDsCategories, languages: this.checkedIDs};
      
    if(this.selectedItemsList.length == 0 && this.selectedItemsListCategories.length == 0){
      this.allCourses$ = this.courseService.getAllAdminCourse();
    }else{
    this.allCourses$ = this.courseService.searchCourses(this.searchParams);
    }
  }

  fetchSelectedItems() {
    this.selectedItemsList = this.checkboxesDataList.filter((value, index) => {
      return value.isChecked
    });
  }

  fetchCheckedIDs() {
    this.checkedIDs = []
    this.checkboxesDataList.forEach((value, index) => {
      if (value.isChecked) {
        let languageDto: ILanguageItem = <ILanguageItem>{};
        languageDto['name'] = value.id;
        this.checkedIDs.push(languageDto);
      }
    });
  }

  fetchSelectedItemsCategories() {
    this.selectedItemsListCategories = this.checkboxesDataListCategories.filter((value, index) => {
      return value.isChecked
    
    });
  }

  fetchCheckedIDsCategories() {
    this.checkedIDsCategories = []
    this.checkboxesDataListCategories.forEach((value, index) => {
      if (value.isChecked) {
        let categoryDto: ICategoryItem = <ICategoryItem>{};
        categoryDto['name'] = value.id;
        this.checkedIDsCategories.push(categoryDto);
      }
    });
  }
}
