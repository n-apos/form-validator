package com.napos.form

public class CompositeFieldSpec<T>(private vararg val specs: FieldSpec<T>) : FieldSpec<T>() {

    override fun check(value: T): Set<InvalidReason> =
        specs.flatMap { it.check(value).toList() }.toSet()

}
