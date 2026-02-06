package com.napos.form


fun main() {

    val formValidator = registrationForm.validator()

    val errors = formValidator.validate(
        RegistrationField.Username to "qads",
        RegistrationField.Password to "as123asd",
        RegistrationField.Age to 31
    )

    println("Checking form...")
    if(errors.isEmpty()) {
        println("Form is valid ✅")
        return
    }
    errors.forEach { (field, err) ->
        if(err.isEmpty()) return@forEach

        println("Field \"$field\" errors ❌")
        err.forEachIndexed { index, reason ->
            println("${index + 1}. $reason")
        }
    }

}