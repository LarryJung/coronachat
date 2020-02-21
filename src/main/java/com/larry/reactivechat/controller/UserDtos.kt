package com.larry.reactivechat.controller

import javax.validation.constraints.Email

data class LoginDto(@Email val email: String, val password: String)
