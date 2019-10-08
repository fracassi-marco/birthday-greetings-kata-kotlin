package kata

import java.io.File

class FileEmployeeRepository(private val file: File) : EmployeeRepository {

    override fun bornOn(month: Int, dayOfMonth: Int): List<Employee> {
        if(!file.exists()) {
            return emptyList()
        }

        return file
            .readLines()
            .drop(1)
            .map { it.split(", ") }
            .filter { it.get(2).split("/").get(1).toInt() == month &&
                    it.get(2).split("/").get(2).toInt() == dayOfMonth}
            .map { Employee(it.get(1)) }
    }
}
