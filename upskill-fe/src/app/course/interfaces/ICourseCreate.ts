import { ICategoryItem } from "./ICategoryItem";
import { ILanguageItem } from "./ILanguageItem";
import { ILectureItem } from "./ILectureItem";

export interface ICourseCreate{

    name: string;
    price: number;
    description: string;
    videoUrl: string;
    imageUrl: File;
    startDate: Date;
    endDate: Date;
    categories: Array<ICategoryItem>;
    languages: Array<ILanguageItem>;
    lectures: Array<ILectureItem>;
    lector: string;
    duration: number;
    skills: string;
    lectorDescription: string;

}