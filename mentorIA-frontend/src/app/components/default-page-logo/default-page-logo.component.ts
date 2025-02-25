import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-default-page-logo',
  standalone: true,
  imports: [],
  templateUrl: './default-page-logo.component.html',
  styleUrl: './default-page-logo.component.css'
})
export class DefaultPageLogoComponent {
  @Input() title: string = "";
  @Input() titleL: string = "";
  @Input() primaryBtnText: string = "";
  @Input() loginForm!: FormGroup;
  @Input() formName!: string;
  @Output() onSubmit = new EventEmitter<void>();
  @Output() goToRegister = new EventEmitter();

  register(){
    this.goToRegister.emit()
  }

  submit(){
    this.onSubmit.emit();
  }
}
