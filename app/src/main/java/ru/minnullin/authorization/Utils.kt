package ru.minnullin.authorization

import java.util.regex.Pattern

/**
 * Minimum is 6 chars. Should be at least one capital letter. Allow only english characters and
 * numbers
 */
fun isValid(password: String) =
    Pattern.compile("[0-9]+").matcher(password).matches()