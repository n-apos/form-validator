package com.napos.form


sealed interface RegistrationField : Field {

    data object Username : RegistrationField
    data object Age : RegistrationField
    data object Password : RegistrationField

}

fun main() {

    val usernameValidator = FieldValidator<RegistrationField, String> {
        field = RegistrationField.Username
        specs += listOf(
            FieldSpec.MaxLength(40),
            FieldSpec.Pattern(".*".toRegex())
        )
    }

    val passwordValidator = FieldValidator<RegistrationField, String> {
        field = RegistrationField.Password
        specs += listOf(
            FieldSpec.MinLength(8),
            FieldSpec.Pattern("[a-zA-Z0-9]*".toRegex())
        )
    }

    val ageValidator = FieldValidator<RegistrationField, Int> {
        field = RegistrationField.Age
        specs += listOf(
            FieldSpec.Range(12..100),
        )
    }

    val formValidator = FormValidator(usernameValidator, passwordValidator, ageValidator)

    val errors = formValidator.validate(
        RegistrationField.Username to "qads",
        RegistrationField.Password to "asd123asd",
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