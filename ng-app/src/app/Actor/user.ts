import {Actor} from './actor';
import {Authority} from './authority';

export class User {
  id:number;
  username:string;
  password:string;
  firstname:string;
  lastname:string;
  email:string;
  enabled:string;
  lastPasswordResetDate:Date;
  actor:Actor;
  authorities:Authority;

}
