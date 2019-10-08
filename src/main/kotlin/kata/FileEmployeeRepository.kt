package kata

import java.io.File

const val HEADER_SIZE = 1
const val FIELD_SEPARATOR = ", "

class FileEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun bornOn(month: Int, dayOfMonth: Int): List<Employee> {
        if(!file.exists()) {
            return emptyList()
        }

        return file
            .readLines()
            .drop(HEADER_SIZE)
            .map { it.split(FIELD_SEPARATOR) }
            .map { Employee(it[1], it[2].asDate()) }
            .filter { it.bornOn(month, dayOfMonth) }
    }
}
