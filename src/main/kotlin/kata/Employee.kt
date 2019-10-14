package kata

import java.time.LocalDate

data class Employee(val name: String, val bornDate: LocalDate, val email: String) {

    fun bornOn(month: Int, dayOfMonth: Int): Boolean {
        return bornDate.monthValue == month && bornDate.dayOfMonth == dayOfMonth
    }
}
