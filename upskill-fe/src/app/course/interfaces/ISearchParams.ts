import { ICategoryItem } from "./ICategoryItem";
import { ILanguageItem } from "./ILanguageItem";

export interface ISearchParams{
    categories: Array<ICategoryItem>;
    languages: Array<ILanguageItem>;
}