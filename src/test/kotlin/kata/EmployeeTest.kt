package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmployeeTest {

    @Test
    fun `was born on specified date`() {
        assertThat(Employee("foo", "2018/01/12".asDate()).bornOn(1, 12)).isTrue()
        assertThat(Employee("foo", "2018/01/13".asDate()).bornOn(1, 12)).isFalse()
        assertThat(Employee("foo", "2018/01/12".asDate()).bornOn(2, 12)).isFalse()
    }
}