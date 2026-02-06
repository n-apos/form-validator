package com.napos.form

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
