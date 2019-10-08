package kata

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FileEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun bornOn(month: Int, dayOfMonth: Int): List<Employee> {
        if(!file.exists()) {
            return emptyList()
        }

        return file
            .readLines()
            .drop(1)
            .map { it.split(", ") }
            .map { Employee(it[1], it[2].asDate()) }
            .filter { it.bornOn(month, dayOfMonth) }
    }
}
