package kata

import java.time.LocalDate.now

data class Date(val month: Int, val dayOfMonth: Int) {

    companion object {
        fun today(): Date {
            return Date(now().monthValue, now().dayOfMonth)
        }
    }
}