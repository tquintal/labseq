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
  inputValueStr = '0';
  error = '';
  data = '';

  constructor(private dataService: DataService) {}

  onValueChange(e: Event) {
    this.inputValueStr = (e.target as HTMLInputElement).value;
  }

  handleInputClick() {
    this.error = '';
  }

  handleCalculate(e: Event) {
    e.preventDefault();

    if (this.inputValueStr.trim() === '' || isNaN(Number(this.inputValueStr))) {
      this.error = 'please insert a valid integer';
      return;
    }

    const inputValue = parseInt(this.inputValueStr);

    if (inputValue < 0) {
      this.error = "can't be negative";
      return;
    }

    if (inputValue > 10000) {
      this.error = 'the maximum value is 10 000';
      return;
    }

    this.value = this.inputValueStr;

    this.dataService.getData(this.inputValueStr).subscribe((data) => {
      this.data = data;
    });

    this.error = '';
  }
}
