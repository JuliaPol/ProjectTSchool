export interface ICustomer {
  firstName: string,
  lastName: string,
  contractList: IContract[],
  selected: boolean;
}

export interface IContract {
  number: string,
  status: string,
}
