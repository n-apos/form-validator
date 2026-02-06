package com.napos.form


sealed interface RegistrationField : Field {

    data object Username : RegistrationField
    data object Age : RegistrationField
    data object Password : RegistrationField

}