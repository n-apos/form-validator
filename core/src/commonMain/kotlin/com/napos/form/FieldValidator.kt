package com.napos.form

public class FieldValidator<F : Field, T> @PublishedApi internal constructor(
    public val field: F,
    public val specs: CompositeFieldSpec<T>
) {

    public fun check(value: T): Set<InvalidReason> =
        specs.check(value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        return field == (other as FieldValidator<*, *>).field
    }

    override fun hashCode(): Int = field.hashCode()

}

public inline fun <reified F : Field, T> FieldValidator(block: FieldValidatorBuilder<F, T>.() -> Unit): FieldValidator<F, T> =
    FieldValidatorBuilder<F, T>()
        .apply(block)
        .build()
