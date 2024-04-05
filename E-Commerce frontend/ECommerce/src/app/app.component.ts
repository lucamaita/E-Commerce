import { Component, OnInit } from '@angular/core';
import { AuthService } from './Services/services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ECommerce';
  constructor(public authService:AuthService, private router : Router) {}
		
  user:any;

  role = {
    ADMIN : 'ADMIN',
    GUEST : 'GUEST'
  }

  ngOnInit(): void {
    this.authService.getUserProfile().subscribe({
      next: (data) => console.log('req user', data), 
      error: (error) => console.log('error', error),
    });
    this.authService.authSubject.subscribe((auth) => {
      // console.log('auth object value', auth);
      this.user = auth.user;
      //console.log("Utente loggato o Null " + this.user.role)
      if(this.user){
        if(this.user.role === this.role.ADMIN){
          this.router.navigate(['/gestionale/ordini'])
        }else
        {
           this.router.navigate(['/home']);
        }
       
      }

    });

  }

}

