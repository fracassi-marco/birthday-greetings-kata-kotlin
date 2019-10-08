package kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate.parse
import java.time.format.DateTimeFormatter

class FileEmployeeRepositoryTest {

    private val file = File("/tmp/employees.txt")

    @BeforeEach
    @AfterEach
    fun clean() {
        file.delete()
    }

    @Test
    fun `empty file`() {
        val employees = FileEmployeeRepository(file).bornOn(10, 8)

        assertThat(employees).isEmpty()
    }

    @Test
    fun `file with header`() {
        file.writeText("last_name, first_name, date_of_birth, email")

        val employees = FileEmployeeRepository(file).bornOn(10, 8)

        assertThat(employees).isEmpty()
    }

    @Test
    fun `file with one record`() {
        file.writeText("last_name, first_name, date_of_birth, email")
        file.appendText("\nDoe, John, 1982/10/08, john.doe@foobar.com")

        val employees = FileEmployeeRepository(file).bornOn(10, 8)

        assertThat(employees.single()).isEqualTo(Employee("John", date("1982/10/08")))
    }

    @Test
    fun `filter by born date`() {
        file.writeText("last_name, first_name, date_of_birth, email")
        file.appendText("\nDoe, John, 1982/10/12, john.doe@foobar.com")
        file.appendText("\nDoe, Alex, 1982/10/09, alex.doe@foobar.com")

        val employees = FileEmployeeRepository(file).bornOn(10, 12)

        assertThat(employees.single()).isEqualTo(Employee("John", date("1982/10/12")))
    }

    private fun date(value: String) = parse(value, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}