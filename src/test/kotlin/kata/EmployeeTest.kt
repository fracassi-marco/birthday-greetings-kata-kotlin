package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EmployeeTest {

    @Test
    fun `was born on specified date`() {
        assertThat(Employee("foo", date("2018/01/12")).bornOn(1, 12)).isTrue()
        assertThat(Employee("foo", date("2018/01/13")).bornOn(1, 12)).isFalse()
        assertThat(Employee("foo", date("2018/01/12")).bornOn(2, 12)).isFalse()
    }

    private fun date(value: String) = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}