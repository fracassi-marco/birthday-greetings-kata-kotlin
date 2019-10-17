package kata

data class Employee(val name: String, val bornDate: Date, val email: String) {

    fun bornOn(date: Date): Boolean {
        return bornDate == date
    }
}
