import {IOption} from "./options";

export interface ITariff {
  id: number,
  description: string,
  cost: number,
  calls: number,
  sms: number,
  internet: number,
  options: IOption[]
}
