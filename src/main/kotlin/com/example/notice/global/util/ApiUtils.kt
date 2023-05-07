package com.example.notice.global.util

import com.example.notice.global.model.IdResponse
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


fun IdResponse.toUri() = ServletUriComponentsBuilder.fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(this.id)
    .toUri()
