package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FileEmployeeRepositoryTest {

    @Test
    fun `empty file`() {
        val employees = FileEmployeeRepository().bornOn(10, 8)

        assertThat(employees).isEmpty()
    }
}