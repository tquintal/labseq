import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  // private apiUrl = 'http://localhost:8080/labseq/10000';
  private apiUrl = 'http://localhost:8080/labseq/27';

  constructor(private http: HttpClient) {}

  getData(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
