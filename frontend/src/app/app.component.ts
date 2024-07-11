import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DataService } from './api';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf],
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'frontend';
  data: any;
  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getData().subscribe((data) => {
      this.data = data;
    });
  }
}
