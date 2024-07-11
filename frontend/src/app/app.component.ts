import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DataService } from './api';
import { NgClass, NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, NgClass],
  templateUrl: './app.component.html',
})
export class AppComponent {
  value = '0';
  inputValue = '0';
  error = '';
  data: any;

  constructor(private dataService: DataService) {}

  onValueChange(e: Event) {
    this.inputValue = (e.target as HTMLInputElement).value;
  }

  handleInputClick() {
    this.error = '';
  }

  handleCalculate() {
    if (this.inputValue.trim() === '' || isNaN(Number(this.inputValue))) {
      this.error = 'please insert a valid integer';
      return;
    }

    if (parseInt(this.inputValue) < 0) {
      this.error = "can't be negative";
      return;
    }

    if (parseInt(this.inputValue) > 10000) {
      this.error = 'the maximum value is 10 000';
      return;
    }

    this.value = this.inputValue;

    this.dataService.getData(this.inputValue).subscribe((data) => {
      this.data = data;
    });

    this.error = '';
  }
}
