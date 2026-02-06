package com.napos.form

public class FormBuilder<F : Field> @PublishedApi internal constructor() {

    public val validators: MutableSet<FieldValidator<*, *>> = mutableSetOf()

    public fun build(): Form<F> =
        Form(validators.toSet())

}

public inline fun <reified F : Field> Form(block: FormBuilder<F>.() -> Unit): Form<F> =
    FormBuilder<F>()
        .apply(block)
        .build()