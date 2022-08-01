
import { Injectable } from '@angular/core';
import { HttpClient,HttpClientModule,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginUrl: string;
  private validateUrl: string;

  constructor(private http:HttpClient) {
    // this.loginUrl='http://35.89.125.172:8090/authapp/login';
    this.loginUrl='http://localhost:8048/auth-service/authapp/login';  //http://localhost:8048/auth-service/authapp/login
    // this.validateUrl='http://35.89.125.172:8090/authapp/validate';
    this.validateUrl='http://localhost:8048/auth-service/authapp/validate';  //http://localhost:8048/auth-service/authapp/validate
   }
   generateToken(user : User): Observable<Object>{
    return this.http.post(this.loginUrl, user,{responseType:'text' as 'json'});
   }
   validateToken(token: string){
    const header = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }
    return this.http.get(`${this.validateUrl}`, header);

  }
  loginMember(user: User) {
    return this.http.post(`${this.loginUrl}`, user);
  }
}
