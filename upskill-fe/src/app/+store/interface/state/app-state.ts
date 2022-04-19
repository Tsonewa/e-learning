
import { IEmployeeRegisterState } from "./employee-register-state";
import { ILoginState } from "./login";

export interface IState {
  auth: ILoginState;
  register:IEmployeeRegisterState
}
