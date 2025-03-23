import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultPageLogoComponent } from '../default-page-logo/default-page-logo.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputComponent } from '../input/input.component';
import { ConexaoService } from '../../services/conexao.service';
import { ToastrService } from 'ngx-toastr';
import { errorContext } from 'rxjs/internal/util/errorContext';

interface LoginForm {
  email: FormControl,
  password: FormControl
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [DefaultPageLogoComponent, ReactiveFormsModule, InputComponent],
  providers:[
    ConexaoService
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{
   loginForm!: FormGroup<LoginForm>;
   private toastService: ToastrService
  
  constructor(private router: Router, private conexaoService: ConexaoService) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  navigate() {
    this.router.navigate(['/register'])
  }

  submit() {
    this.conexaoService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe({
      next: () => {
        this.router.navigate(['/roomIA'])
      },
      error: (error) => {console.log("Error no login:", error)} 
    })
  }
}


