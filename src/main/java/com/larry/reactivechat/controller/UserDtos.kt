package com.larry.reactivechat.controller

import javax.validation.constraints.Email

data class LoginDto(@Email val email: String, val password: String)
data class Principal(val id: Long, val email: String, val name: String)