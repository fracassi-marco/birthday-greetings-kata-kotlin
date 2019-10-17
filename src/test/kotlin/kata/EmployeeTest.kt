package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmployeeTest {

    @Test
    fun `was born on specified date`() {
        assertThat(Employee("foo", "2018/01/12".asDate(), "a@b.c").bornOn(Date(1, 12))).isTrue()
        assertThat(Employee("foo", "2018/01/13".asDate(), "a@b.c").bornOn(Date(1, 12))).isFalse()
        assertThat(Employee("foo", "2018/01/12".asDate(), "a@b.c").bornOn(Date(2, 12))).isFalse()
    }
}