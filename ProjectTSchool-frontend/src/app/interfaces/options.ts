export interface IOption {
  id: number,
  description: string,
  cost: number,
  costOfConnection: number,
  name: string,
  image: string,
  compatibleOptions: string[],
  incompatibleOptions: string[],
  compatibleOptionsOf: string[]
}
