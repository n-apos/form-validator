package com.napos.form

@Suppress("UNCHECKED_CAST")
public class Form<F: Field> internal constructor(
    private val validators: Set<FieldValidator<*, *>>
) {

    public fun validator(): FormValidator<F> = FormValidator(*validators.toTypedArray() as Array<out FieldValidator<F, *>>)
}
