//This file defines the User model used in the application

export interface UserLoginRequest{
    phoneNumber: string,
    password: string
}

export interface UserRegisterRequest{
    phoneNumber: string,
    email: string,
    password: string
}

export interface UserResponse{
    id: string,
    phoneNumber: string,
    email: string,
    balance: number,
    wagered: number,
    createdAt: string
}