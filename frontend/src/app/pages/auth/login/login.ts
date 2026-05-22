import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { UserService } from '../../../core/services/user';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { UserLoginRequest } from '../../../core/models/user.model';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private UserService = inject(UserService);

  private router = inject(Router);

  loginForm = new FormGroup({
      phoneNumber : new FormControl('', [Validators.required]),
      password : new FormControl('', [Validators.required])
  })

  onSubmit() {
    if (this.loginForm.valid) {
      const user: UserLoginRequest = this.loginForm.value as UserLoginRequest;
      this.UserService.login(user).subscribe({
        next: (response) => {
          this.router.navigate(['/dashboard']);
          this.loginForm.reset();
        },
        error: (error) => {
          console.error('ERROR: Login failed:', error);
        }
      });
    }
  }
}
