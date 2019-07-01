package com.code.refactoring

class Response{

    private val resultcode:Int? = 0
    private val reason:String? = null

    override fun toString(): String {
        return "resultcode = "+resultcode+" reason = "+reason
    }
}