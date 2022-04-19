export interface ICoachesEdit{
  id: string;
  name: string;
  email: string;
  company: string;
  topic: string;
  picture: File;
  price: BigInteger;
  startDate: Date;
  endDate: Date;
}
