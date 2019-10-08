package kata

class FileEmployeeRepository : EmployeeRepository{

    override fun bornOn(month: Int, dayOfMonth: Int): List<Employee> {
        return emptyList()
    }
}
