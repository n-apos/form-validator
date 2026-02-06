package com.napos.form

public class FieldValidatorBuilder<F : Field, T> @PublishedApi internal constructor() {

    public lateinit var field: F
    public val specs: MutableList<FieldSpec<T>> = mutableListOf()

    public fun build(): FieldValidator<F, T> =
        FieldValidator(field, CompositeFieldSpec(*specs.toTypedArray()))

}
