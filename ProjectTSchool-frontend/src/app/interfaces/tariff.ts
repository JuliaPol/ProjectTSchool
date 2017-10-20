import {IOption} from "./options";

export interface ITariff {
  id: number,
  name: string,
  description: string,
  cost: number,
  calls: number,
  sms: number,
  internet: number,
  optionList: IOption[]
}
