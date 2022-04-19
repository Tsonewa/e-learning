import {ILoginState} from '../interface/state'
export const getToken = (state:ILoginState)=>(state.token);
export const getEmail = (state:ILoginState)=>(state.email);
export const getRole = (state: ILoginState) => (state.role);