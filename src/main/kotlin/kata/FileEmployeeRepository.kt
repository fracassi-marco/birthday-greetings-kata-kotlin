package kata

import java.io.File

const val FIELD_SEPARATOR = ", "

class FileEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun bornOn(month: Int, dayOfMonth: Int): List<Employee> {
        if(!file.exists()) {
            return emptyList()
        }

        return file
            .readLines()
            .skipHeader()
            .map { it.split(FIELD_SEPARATOR) }
            .map { Employee(it[1], it[2].asDate(), "a@b.c") }
            .filter { it.bornOn(month, dayOfMonth) }
    }

    private fun List<String>.skipHeader() : List<String>{
        return this.drop(1)
    }
}
