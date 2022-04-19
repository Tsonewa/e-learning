export interface ICoachesCreate{

    id: string;
    name: string;
    email: string;
    company: string;
    topic: string;
    picture: File;
    price: BigInteger;
    startDate: Date;
    endDate: Date;
    description: string;
    duration: string;
    introductionVideo: string;
    goals: string;
    categories: Array<[]>;
    languages: Array<[]>;
    resources: Array<[]>;
    owned: boolean;
}
