package com.napos.form

public class FormValidator<F : Field>(public vararg val fieldValidator: FieldValidator<F, *>) {

    @Suppress("UNCHECKED_CAST")
    private fun <T> FieldValidator<F, T>.checkAny(value: Any?): Set<InvalidReason> =
        this.check(value as T)

    public fun validate(vararg value: Pair<F, Any?>): Map<F, Set<InvalidReason>> =
        value.mapNotNull { (f, v) ->
            val validator = fieldValidator.firstOrNull { it.field == f } ?: return@mapNotNull null
            val errors = validator.checkAny(v)
            if(errors.isNotEmpty())
                f to errors
            else
                null
        }
            .associate { it }

}
