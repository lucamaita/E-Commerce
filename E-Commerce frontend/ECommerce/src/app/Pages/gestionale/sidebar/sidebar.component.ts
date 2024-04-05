import { Component, HostListener, OnInit, Input } from '@angular/core';
// Assicurati che il percorso qui corrisponda esattamente alla posizione del file del tuo ApiService
import { ApiService } from 'src/app/Services/api.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Input() isActive: boolean = false; 

  isDarkTheme: boolean = false;

  constructor(private apiService: ApiService) { }

  @HostListener('document:mousemove', ['$event'])
  onMouseMove(e: MouseEvent): void {
    this.isActive = e.pageX < 50; 
  }
  
  closeNav(): void {
    this.isActive = false;
  }

  ngOnInit(): void {
    this.loadTheme();
  }

  toggleTheme(): void {
    this.isDarkTheme = !this.isDarkTheme;
    this.applyTheme();
  }

  applyTheme(): void {
    const themeClass = this.isDarkTheme ? 'dark-theme' : 'light-theme';
    document.body.classList.remove('dark-theme', 'light-theme'); // Rimuove entrambe le classi per evitare conflitti
    document.body.classList.add(themeClass);
    this.saveTheme();
  }

  saveTheme(): void {
    localStorage.setItem('theme', this.isDarkTheme ? 'dark' : 'light');
  }

  loadTheme(): void {
    const savedTheme = localStorage.getItem('theme');
    this.isDarkTheme = savedTheme === 'dark';
    this.applyTheme(); 
  }
  showAddProductForm(){
    return 0;

  }
}