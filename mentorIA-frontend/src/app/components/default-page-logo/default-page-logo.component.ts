import { Component, EventEmitter, Input, Output } from '@angular/core';

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


  @Output() goToRegister = new EventEmitter();

  Register(){
    this.goToRegister.emit()
  }
}
