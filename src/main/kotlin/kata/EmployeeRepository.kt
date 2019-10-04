package kata

interface EmployeeRepository {
    fun bornOn(month: Int, dayOfMonth: Int): List<Employee>
}
