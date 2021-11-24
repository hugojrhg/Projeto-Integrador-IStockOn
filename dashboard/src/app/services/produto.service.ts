import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Produto } from '../models/produto';
import { Observable } from 'rxjs/internal/Observable';
import { retry } from 'rxjs/internal/operators/retry';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  url = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  getProdutos(): Observable<Produto[]> {
    return this.httpClient.get<Produto[]>(this.url + 'produto')
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  saveProduto(produto: Produto): Observable<Produto> {
    return this.httpClient.post<Produto>(this.url + 'produto', JSON.stringify(produto), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  updateProduto(produto: Produto): Observable<Produto> {
    return this.httpClient.put<Produto>(this.url + 'produto' + '/' + produto.id, JSON.stringify(produto), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  deleteProduto(produto: Produto) {
    return this.httpClient.delete<Produto>(this.url + 'produto' + '/' + produto.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getLucro(): Observable<number> {
    return this.httpClient.get<number>(this.url + 'lucro')
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

}
