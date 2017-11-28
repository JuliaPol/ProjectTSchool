export interface ITariff {
  id: number,
  name: string,
  description: string,
  cost: number,
  calls: number,
  sms: number,
  internet: number,
  image: string,
  optionList: any[]
}
