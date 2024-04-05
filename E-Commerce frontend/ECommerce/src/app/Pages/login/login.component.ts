import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/Services/services/auth.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) { }


  // loginForm = new FormGroup({
  //   email:new FormControl('',[Validators.required,Validators.email]),
  //   password:new FormControl('', [Validators.required]),
  // });

  loginForm = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.minLength(8),]),
  });

  signIn() {
    this.authService.login({ email: this.username, password: this.password }).subscribe({
      next: (response) => {
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("login success", response)
        window.location.reload();
      },
      error: (error) => {
        console.log(error.error.trace);
      }
    })

  }

  signUp(): void {
    this.authService.register({ email: this.username, password: this.password })
      .subscribe(response => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log('signup success', response);
        window.location.reload();
      });
  }
}