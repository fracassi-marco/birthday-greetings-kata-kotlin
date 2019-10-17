package kata

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun  String.asDate() : Date {
    val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
    return Date(localDate.monthValue, localDate.dayOfMonth)
}