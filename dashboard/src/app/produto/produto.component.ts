import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Produto } from '../models/produto';
import { ProdutoService } from '../services/produto.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  produto = {} as Produto;
  produtos: Produto[];

  constructor(private produtoService: ProdutoService) { }

  ngOnInit() {
    this.getProdutos();
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

}
