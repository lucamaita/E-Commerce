import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Importa il Router

@Component({
  selector: 'app-site',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.css']
})
export class SiteComponent implements OnInit {
  private slideIndex = 0;

  constructor() {
  }

  ngOnInit(): void {
    this.showSlides();

  }

  private showSlides(): void {
    const slides = document.getElementsByClassName('carousel-image') as HTMLCollectionOf<HTMLElement>;
    Array.from(slides).forEach((slide: HTMLElement) => slide.style.display = 'none');
    this.slideIndex++;
    if (this.slideIndex > slides.length) { this.slideIndex = 1; }
    slides[this.slideIndex - 1].style.display = 'block';
    setTimeout(() => this.showSlides(), 3000);
  }

}