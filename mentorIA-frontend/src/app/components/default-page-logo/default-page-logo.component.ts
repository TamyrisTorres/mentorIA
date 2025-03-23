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
  @Input() secondBtnText: string = "";
  @Input() disabledPrimaryBtn: boolean = true;
  @Output("submit") onSubmit = new EventEmitter();
  @Output("navigate") onNavigate = new EventEmitter();

  navigate(){
    this.onNavigate.emit()
  }

  submit(){
    this.onSubmit.emit();
  }
}
