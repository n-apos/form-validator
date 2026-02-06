package com.napos.form


public interface Field


public enum class InvalidReason {
    Required,
    ValueTooShort,
    ValueTooLong,
    InvalidFormat,
    OutOfRange,
    ;
}

public class FieldValidator<F : Field, T> @PublishedApi internal constructor(
    public val field: F,
    public val specs: CompositeFieldSpec<T>
) {

    public fun check(value: T): Set<InvalidReason> =
        specs.check(value)

}

public class FieldValidatorBuilder<F : Field, T> @PublishedApi internal constructor() {

    public lateinit var field: F
    public val specs: MutableList<FieldSpec<T>> = mutableListOf()

    public fun build(): FieldValidator<F, T> =
        FieldValidator(field, CompositeFieldSpec(*specs.toTypedArray()))

}

public inline fun <reified F : Field, T> FieldValidator(block: FieldValidatorBuilder<F, T>.() -> Unit): FieldValidator<F, T> =
    FieldValidatorBuilder<F, T>()
        .apply(block)
        .build()


public sealed class FieldSpec<T> {
    public abstract fun check(value: T): Set<InvalidReason>


    public data class MinLength(val length: Int) : FieldSpec<String>() {

        override fun check(value: String): Set<InvalidReason> =
            if (value.length >= length)
                emptySet()
            else
                setOf(InvalidReason.ValueTooShort)
    }

    public data class MaxLength(val length: Int) : FieldSpec<String>() {

        override fun check(value: String): Set<InvalidReason> =
            if (value.length <= length)
                emptySet()
            else
                setOf(InvalidReason.ValueTooLong)
    }

    public data class Pattern(val regex: Regex) : FieldSpec<String>() {

        override fun check(value: String): Set<InvalidReason> =
            if (value.matches(regex))
                emptySet()
            else
                setOf(InvalidReason.InvalidFormat)

    }

    public data class Range(val range: IntRange) : FieldSpec<Int>() {

        override fun check(value: Int): Set<InvalidReason> =
            if (value in range)
                emptySet()
            else
                setOf(InvalidReason.OutOfRange)

    }

}

public class CompositeFieldSpec<T>(private vararg val specs: FieldSpec<T>) : FieldSpec<T>() {

    override fun check(value: T): Set<InvalidReason> =
        specs.flatMap { it.check(value).toList() }.toSet()

}




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

