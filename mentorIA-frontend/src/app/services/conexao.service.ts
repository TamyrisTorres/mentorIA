import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServiceResponse } from '../types/serviceResponse.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConexaoService {

  completUrl: string;
  apiUrl: string = "http://localhost:3006/auth/";

  constructor(private httpClient: HttpClient) {}
  
  login(email: String, password: String){
    this.completUrl = this.apiUrl + "login";
    
    return this.httpClient.post<ServiceResponse>(this.completUrl,
       {email, password}).pipe(
      tap((valeu) => {
        sessionStorage.setItem("auth-token", valeu.token),
        sessionStorage.setItem("username", valeu.email)
      })
    )
  }


  register(nome: String, email: String, password: String){
    this.completUrl = this.apiUrl + "register";
   
    return this.httpClient.post<ServiceResponse>(this.completUrl,
       {nome, email, password}).pipe(
        tap((valeu) => {
          sessionStorage.setItem("nome", valeu.nome)
          sessionStorage.setItem("auth-token", valeu.token),
          sessionStorage.setItem("username", valeu.email)
        })
       )
  }
}
