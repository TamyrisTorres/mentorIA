import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultPageLogoComponent } from '../default-page-logo/default-page-logo.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputComponent } from '../input/input.component';

interface LoginForm {
  email: FormControl,
  password: FormControl
}


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [DefaultPageLogoComponent, ReactiveFormsModule, InputComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup;

  ngOnInit(): void {
  this.loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });
}

  constructor(private router: Router) {
  }

  goToRegister() {
    this.router.navigate(['/register'])
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log("Dados do Formulário:", this.loginForm.value);
    } else {
      console.log("Formulário inválido");
    }
  }
 
}
