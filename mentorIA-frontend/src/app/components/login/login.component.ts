import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultPageLogoComponent } from '../default-page-logo/default-page-logo.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputComponent } from '../input/input.component';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [DefaultPageLogoComponent, ReactiveFormsModule, InputComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    })
  }

  goToRegister() {
    this.router.navigate(['/register'])
  }
 
}
