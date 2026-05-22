import { Component, inject } from '@angular/core';
import { UserService } from '../../../core/services/user';
import { FormControl, Validators, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UserRegisterRequest } from '../../../core/models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})

export class Register {

  private userService = inject(UserService);
  private router = inject(Router);

  registerForm = new FormGroup({
     phoneNumber: new FormControl('', Validators.required),
     email: new FormControl('', [Validators.required, Validators.email]),
     password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  })

  onSubmit(){
    if (this.registerForm.invalid) return;

    const formValue: UserRegisterRequest = {
      phoneNumber: this.registerForm.value.phoneNumber!,
      email: this.registerForm.value.email!,
      password: this.registerForm.value.password!,
    };

    this.userService.register(formValue).subscribe({
      next: (response) => {
        this.router.navigate(['/dashboard']);
        this.registerForm.reset(); 
      },
      error: (error) => {
        console.error('ERROR: Registration failed:', error);
      },
      complete: () => console.log('Registration process completed')
    })

  }
}
  