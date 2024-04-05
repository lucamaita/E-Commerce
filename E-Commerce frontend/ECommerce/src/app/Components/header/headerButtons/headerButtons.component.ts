import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/Services/api.service';

@Component({
  selector: 'app-header-buttons',
  templateUrl: './headerButtons.component.html',
  styleUrls: ['./headerButtons.component.css']
})
export class HeaderButtonsComponent {

  isShowable: boolean = false;
  isHovering: boolean = false;
  private timeoutId: any;

  constructor(private router: Router, private apiService: ApiService, private elementRef: ElementRef) { }


  navigateToOther() {
    this.router.navigate(['/shop']);
  }

  funzioneProvaGet() {
    this.apiService.apiGET('http://localhost:5454/products/getAll').subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  navigateToDonna(rotta: String) {
    this.router.navigate([rotta]);
  }


  //----------------------------------------------

  showComponent() {
    clearTimeout(this.timeoutId);
    this.isShowable = true;
  }

  hideComponent(event: MouseEvent) {
    this.timeoutId = setTimeout(() => {
      if (!this.isMouseInside(event)) {
        this.isShowable = false;
      }
    }, 125);
  }

  isMouseInside(event: MouseEvent): boolean {
    const componentRect = this.elementRef.nativeElement.getBoundingClientRect();
    return (
      componentRect.left <= event.clientX &&
      componentRect.right >= event.clientX &&
      componentRect.top <= event.clientY &&
      componentRect.bottom >= event.clientY
    );
  }
}

