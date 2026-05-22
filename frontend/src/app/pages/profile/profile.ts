import { Component, OnInit, signal } from '@angular/core';
import { UserService } from '../../core/services/user';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserRegisterRequest, UserResponse } from '../../core/models/user.model';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { FormGroup } from '@angular/forms';


@Component({
  selector: 'app-profile',
  imports: [ReactiveFormsModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})

export class Profile implements OnInit {
  private userService = inject(UserService);
  private router = inject(Router);

  private userId: string = sessionStorage.getItem('userId') || ''; 
  userProfile = signal<UserResponse | null>(null);


  userForm = new FormGroup({
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
  });

  ngOnInit() {
    this.loadUserProfile();
  }

  loadUserProfile() {
    this.userService.getUserProfile(this.userId).subscribe({
      next: (response) => {
        this.userProfile.set(response);
        console.log('User profile loaded successfully:', this.userProfile());
        // Populate form with existing user data
        this.userForm.patchValue({
          phoneNumber: response.phoneNumber,
          email: response.email,
        });
      },
      error: (error) => {
        console.error('ERROR: Failed to load user profile:', error);
      },
      complete: () => console.log('User profile loading process completed')
    });
  }

  updateProfile() {
    if (this.userForm.valid) {
       const updatedUser: Omit<UserRegisterRequest, 'password'> = {
        phoneNumber: this.userForm.value.phoneNumber || '' ,
        email: this.userForm.value.email || '',
      };
      this.userService.updateUserProfile(this.userId, updatedUser).subscribe({
        next: (response) => {
          this.userProfile.set(response);
        },
        error: (error) => {
          console.error('ERROR: Failed to update user profile:', error);
          alert('Failed to update profile. Please try again later.');
        },
        complete: () => console.log('User profile update process completed')
      });
    }
  }

  deleteProfile() {
    if (confirm('Are you sure you want to delete your profile? This action cannot be undone.')) {
      this.userService.deleteUserProfile(this.userId).subscribe({
        next: () => {
          alert('Profile deleted successfully.');
          this.logout();
        },
        error: (error) => {
          console.error('ERROR: Failed to delete user profile:', error);
          alert('Failed to delete profile. Please try again later.');
        },
        complete: () => console.log('User profile deletion process completed')
      });
    }
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/login']);
  }

  
}
