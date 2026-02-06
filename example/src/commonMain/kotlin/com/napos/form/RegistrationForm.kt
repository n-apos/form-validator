package com.napos.form

val registrationForm = Form<RegistrationField> {
    validators += FieldValidator<RegistrationField, String> {
        field = RegistrationField.Username
        specs += listOf(
            FieldSpec.MaxLength(40),
            FieldSpec.Pattern(".*".toRegex())
        )
    }

    validators += FieldValidator<RegistrationField, String> {
        field = RegistrationField.Password
        specs += listOf(
            FieldSpec.MinLength(8),
            FieldSpec.Pattern("[a-zA-Z0-9]*".toRegex())
        )
    }

    validators += FieldValidator<RegistrationField, Int> {
        field = RegistrationField.Age
        specs += listOf(
            FieldSpec.Range(12..100),
        )
    }

}