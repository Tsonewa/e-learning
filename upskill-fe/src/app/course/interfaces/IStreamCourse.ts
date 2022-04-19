import { ILectureItem } from "./ILectureItem";

interface IStreamCourse {
    name: string;
    lector: string;
    lectorDescription:string;
    lectures:Array<ILectureItem>;
}

export default IStreamCourse;