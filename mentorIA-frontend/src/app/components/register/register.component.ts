import {Component } from '@angular/core';
import { DefaultPageLogoComponent } from '../default-page-logo/default-page-logo.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputComponent } from '../input/input.component';
import { ConexaoService } from '../../services/conexao.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

interface RegisterForm{
  nomeCompleto: FormControl,
  email: FormControl,
  password: FormControl,
  passwordConf: FormControl
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [DefaultPageLogoComponent, ReactiveFormsModule, InputComponent],
  providers:[
      ConexaoService
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm!: FormGroup<RegisterForm>;
  private toastService: ToastrService

  constructor(private router: Router, private conexaoService: ConexaoService){

     this. registerForm = new FormGroup({
      nomeCompleto: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      passwordConf: new FormControl('', [Validators.required, Validators.minLength(6)])
     });
  }


  navigate() {
    this.router.navigate(['/login'])
  }

  submit() {
    this.conexaoService.register(this.registerForm.value.nomeCompleto, this.registerForm.value.email, 
      this.registerForm.value.password).subscribe({
      next: () => this.router.navigate(['/roomIA'])
    })
  }
}


