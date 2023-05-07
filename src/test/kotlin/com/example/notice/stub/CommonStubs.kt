package com.example.notice.stub

import com.example.notice.global.model.IdResponse

fun getIdResponse() = IdResponse(
    getId()
)

fun getId() = 0L