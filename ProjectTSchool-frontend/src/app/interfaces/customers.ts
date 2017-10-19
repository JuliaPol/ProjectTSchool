export interface ICustomer {
  firstName: string,
  lastName: string,
  contractList: IContract[],
  selected: boolean;
  email: string;
  birthDate: string;
  passportNumber: string;
  passportIssuedWhen: string;
  passportIssuedByWhom: string;
  address: IAddress;
}

export interface IContract {
  number: string,
  status: string,
}

export interface IAddress {
  country: string,
  city: string,
  street: string,
  zipcode: string,
  houseNumber: string,
}
