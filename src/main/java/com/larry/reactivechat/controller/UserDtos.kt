package com.larry.reactivechat.controller

import com.fasterxml.jackson.annotation.JsonCreator
import lombok.NoArgsConstructor
import javax.validation.constraints.Email

data class LoginDto(@Email val email: String, val password: String)
data class Principal(val id: Long, val email: String, val name: String)