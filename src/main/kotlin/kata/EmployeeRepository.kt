package kata

interface EmployeeRepository {
    fun bornOn(date: Date): List<Employee>
}
