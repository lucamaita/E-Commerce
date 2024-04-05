import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/Services/services/auth.service';
import { ApiService } from 'src/app/Services/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private authService: AuthService, private apiService: ApiService) {

  }

  user: any;
  ngOnInit(): void {
    this.authService.getUserProfile().subscribe({
      next: (data) => {
        this.user = data;
        console.log("Header : utente -> " + this.user.value);
        // this.userLogged();
      },
      error: (error) => {
        console.log("Errore " + error.error.message)
      }
    })
  }

  userLogged(): boolean {
    return this.user !== undefined ? true : false;
  }

  navigateTo(rotta: string) {
    this.apiService.navigateTo(rotta);
  }


  // visibility() {
  //   this.isShowable = !this.isShowable;
  // }

  // showComponent() {
  //   this.isShowable = true; // Mostra il componente
  // }

  // hideComponent() {
  //   this.isShowable = false; // Nascondi il componente
  // }

  logout() {
    this.authService.logout()
  }
}
