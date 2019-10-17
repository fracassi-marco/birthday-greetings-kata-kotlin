package kata

import java.io.File

private const val FIELD_SEPARATOR = ", "
private const val NAME_INDEX = 1
private const val BORN_DATE = 2

class FileEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun bornOn(date: Date): List<Employee> {
        if(!file.exists()) {
            return emptyList()
        }

        return file
            .readLines()
            .skipHeader()
            .map { it.split(FIELD_SEPARATOR) }
            .map { Employee(it[NAME_INDEX], it[BORN_DATE].asDate(), "a@b.c") }
            .filter { it.bornOn(date) }
    }

    private fun List<String>.skipHeader() : List<String>{
        return this.drop(NAME_INDEX)
    }
}
