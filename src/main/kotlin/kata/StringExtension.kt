package kata

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun  String.asDate() : LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}