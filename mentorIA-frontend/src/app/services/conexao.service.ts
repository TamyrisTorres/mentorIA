import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiceResponse } from '../types/serviceResponse.type';
import { tap } from 'rxjs';
import { IAResponse } from '../types/iaResponse.type';

@Injectable({
  providedIn: 'root'
})
export class ConexaoService {

  completUrl: string;
  apiUrl: string = "http://localhost:3006/";

  constructor(private httpClient: HttpClient) {}

  login(email: String, password: String){
    this.completUrl = this.apiUrl + "auth/login";

    return this.httpClient.post<ServiceResponse>(this.completUrl,
       {email, password}).pipe(
      tap((valeu) => {
        sessionStorage.setItem("auth-token", valeu.token),
        sessionStorage.setItem("username", valeu.email)
      })
    )
  }


  register(nome: String, email: String, password: String){
    this.completUrl = this.apiUrl + "createUser/register";

    return this.httpClient.post<ServiceResponse>(this.completUrl,
       {nome, email, password}).pipe(
        tap((valeu) => {
          sessionStorage.setItem("nome", valeu.nome)
          sessionStorage.setItem("auth-token", valeu.token),
          sessionStorage.setItem("username", valeu.email)
        })
       )
  }

  sendQuestionIa(question: string) {
    this.completUrl = this.apiUrl + "api/ia/ask";
  
    const token = sessionStorage.getItem("auth-token");
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  
    return this.httpClient.post<IAResponse>(
      this.completUrl,
      { question },
      { headers }
    ).pipe(
      tap((valeu) => {
        sessionStorage.setItem("question", valeu.question);
      })
    );
  }
  
}
