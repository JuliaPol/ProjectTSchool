import {Pipe, PipeTransform} from "@angular/core";
import {ICustomer} from "../../interfaces/customers";

@Pipe({
  name: 'customerContractNumberFiler'
})
export class CustomerContractNumberFiler implements PipeTransform {

  transform(customers: ICustomer[], args: any[]): any {
    if (!args) {
      return customers;
    }
    return customers.filter(customer => this.existsNumber(customer, args) !== -1);
  }

  existsNumber(customer: ICustomer, args: any) {
    let flag: number = -1;
    if (customer.contractList.length === 0) {
      return flag;
    }
      customer.contractList.forEach((contract) => {
         if (flag !== 1 && contract.number.indexOf(args) !== -1) {
           flag = 1;
         }
      });
    return flag;
  }
}
