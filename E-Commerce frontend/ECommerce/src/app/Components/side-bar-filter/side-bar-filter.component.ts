import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-side-bar-filter',
  templateUrl: './side-bar-filter.component.html',
  styleUrls: ['./side-bar-filter.component.css']
})
export class SideBarFilterComponent {
  @Output() pippoUno: EventEmitter<any> = new EventEmitter<any>();

  pippo(pippa: any) {
    this.pippoUno.emit(pippa);
  }
}
