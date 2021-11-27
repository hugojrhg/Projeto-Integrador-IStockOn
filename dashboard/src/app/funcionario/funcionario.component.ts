import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Funcionario } from '../models/funcionario';
import { FuncionarioService } from '../services/funcionario.service';


@Component({
  selector: 'app-funcionario',
  templateUrl: './funcionario.component.html',
  styleUrls: ['./funcionario.component.css']
})
export class FuncionarioComponent implements OnInit {

  funcionario = {} as Funcionario;
  funcionarios: Funcionario[];

  constructor(private funcionarioService: FuncionarioService) { }

  ngOnInit() {
    this.getFuncionarios();
  }

  getFuncionarios() {
    this.funcionarioService.getFuncionarios().subscribe((funcionarios: Funcionario[]) => {
      this.funcionarios = funcionarios;
    });
  }

  public saveFuncionario(form: NgForm) {
    if (this.funcionarios.length == 0) {
      this.funcionarioService.saveFuncionario(this.funcionario).subscribe(() => {
        this.cleanForm(form);
      });
    }
    for (let i = 0; i < this.funcionarios.length; i++) {
      if (this.funcionario.id == this.funcionarios[i].id) {
        this.funcionarioService.updateFuncionario(this.funcionario).subscribe(() => {
          this.cleanForm(form);
        });
      }else {
        this.funcionarioService.saveFuncionario(this.funcionario).subscribe(() => {
          this.cleanForm(form);
        });
      }
    }
  }
  deleteFuncionario(funcionario: Funcionario) {
    this.funcionarioService.deleteFuncionario(funcionario).subscribe(() => {
      this.getFuncionarios();
    });
  }

  public editFuncionario(funcionario: Funcionario) {
    this.funcionario = { ...funcionario };
  }
  // limpa o formulario
  cleanForm(form: NgForm) {
    this.getFuncionarios();
    form.resetForm();
    this.funcionario = {} as Funcionario;
  }

}
