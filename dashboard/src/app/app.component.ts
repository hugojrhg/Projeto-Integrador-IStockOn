import { _isNumberValue } from '@angular/cdk/coercion';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Funcionario } from './models/funcionario';
import { Produto } from './models/produto';
import { FuncionarioService } from './services/funcionario.service';
import { ProdutoService } from './services/produto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'dashboard';

  produto = {} as Produto;
  produtos: Produto[];

  funcionario = {} as Funcionario;
  funcionarios: Funcionario[];

  constructor(private produtoService: ProdutoService, private funcionarioService: FuncionarioService) { }

  ngOnInit() {
    this.getProdutos();
    this.getFuncionarios();
  }

  getProdutos() {
    this.produtoService.getProdutos().subscribe((produtos: Produto[]) => {
      this.produtos = produtos;
    });
  }

  public saveProduto(form: NgForm) {
    for (let i = 0; i < this.produtos.length; i++) {
      if (this.produto.id == this.produtos[i].id) {
        this.produtoService.updateProduto(this.produto).subscribe(() => {
          this.cleanForm(form);
        });
      } else {
        this.produtoService.saveProduto(this.produto).subscribe(() => {
          this.cleanForm(form);
        });
      }
    }
  }
  deleteProduto(produto: Produto) {
    this.produtoService.deleteProduto(produto).subscribe(() => {
      this.getProdutos();
    });
  }

  public editProduto(produto: Produto) {
    this.produto = { ...produto };
  }
  // limpa o formulario
  cleanForm(form: NgForm) {
    this.getProdutos();
    form.resetForm();
    this.produto = {} as Produto;
  }

  getFuncionarios() {
    this.funcionarioService.getFuncionarios().subscribe((funcionarios: Funcionario[]) => {
      this.funcionarios = funcionarios;
    });
  }

  public saveFuncionario(form: NgForm) {
    for (let i = 0; i < this.funcionarios.length; i++) {
      if (this.funcionario.id == this.funcionarios[i].id) {
        this.funcionarioService.updateFuncionario(this.funcionario).subscribe(() => {
          this.cleanForm(form);
        });
      } else {
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
  cleanarForm(form: NgForm) {
    this.getFuncionarios();
    form.resetForm();
    this.funcionario = {} as Funcionario;
  }



}
