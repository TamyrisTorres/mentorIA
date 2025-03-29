import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiceResponse } from '../types/serviceResponse.type';
import { tap } from 'rxjs';

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

    sendQuestionIa(question: string){
      this.completUrl = this.apiUrl + "/chat";

      return this.httpClient.post<IAResponse>(
        this.completUrl, {question}).pipe(
          tap((valeu) => {
            sessionStorage.setItem("question", valeu.question)
            })
          )
    }
}
