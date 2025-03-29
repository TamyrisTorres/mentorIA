import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';

type inputTypes = "text";

@Component({
  selector: 'app-room-ia',
  standalone: true,
  imports: [ReactiveFormsModule],
  providers:[
      provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => InputComponent),
            multi: true
    ],
  templateUrl: './room-ia.component.html',
  styleUrl: './room-ia.component.css'
})
export class RoomIAComponent implements ControlValueAccessor {
  @Input() type: InputTypes = "text";

  constructor(private router: Router, private conexaoService: ConexaoService){
      this.questionForIa = newFormGroup({
        questionForIa: newFormGroup('', [Validators.required])
  });
}


  sendQuestion(){
    this.conexaoService.sendQuestion(this.questionForIa.value.question).subscribe({
      next: () => {
        console.log("Sucesso");
        },
      error: (error) => {console.log("Falhou")}
        })
      }
  }
