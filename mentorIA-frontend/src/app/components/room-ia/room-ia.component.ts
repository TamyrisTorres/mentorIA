import { Component, EventEmitter, forwardRef, Input, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ConexaoService } from '../../services/conexao.service';

@Component({
  selector: 'app-room-ia',
  standalone: true,
  imports: [FormsModule],

  templateUrl: './room-ia.component.html',
  styleUrl: './room-ia.component.css'
})
export class RoomIAComponent {
  question: string = "";
  responseMessage: string = "";

  constructor(private router: Router, private conexaoService: ConexaoService){
  }

  sendQuestion(){
    if (this.question.trim()) {
      this.conexaoService.sendQuestionIa(this.question).subscribe({
        next: (response) => {console.log("Pergunta enviada com sucesso", response);
          this.getAnswer(response.answer);
        },
        error: (error) => console.log("Falha ao enviar a pergunta"),        
      });
      
      this.question = "";    
    }
  }
  
  getAnswer(answer: string) {
    this.responseMessage = answer;
    const responseElement = document.getElementsByClassName("responseIa")[0];
    if (responseElement) {
      (responseElement as HTMLElement).style.display = "flex";
    }
  }
}

