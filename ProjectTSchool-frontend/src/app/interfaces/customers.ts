import {ITariff} from "./tariff";
import {IOption} from "./options";

export interface ICustomer {
  id: number,
  firstName: string,
  lastName: string,
  contractList: IContract[],
  selected: boolean;
  email: string;
  login: string;
  password: string;
  birthDate: string;
  registrationDate: string;
  passportNumber: string;
  passportIssuedWhen: string;
  passportIssuedByWhom: string;
  comment: string;
  address: IAddress;
}

export interface IContract {
  id: number,
  number: string,
  rate: ITariff,
  status: string,
  creationDate: string,
  optionList: IOption[],
}

export interface IAddress {
  country: string,
  city: string,
  street: string,
  zipcode: string,
  houseNumber: string,
}
