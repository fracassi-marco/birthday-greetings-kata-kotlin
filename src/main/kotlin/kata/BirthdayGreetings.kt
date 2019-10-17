package kata

import kata.Date.Companion.today

class BirthdayGreetings(private val employeeRepository: EmployeeRepository, private val notifier: Notifier) {

    fun start() {
        employeeRepository
            .bornOn(today())
            .forEach { notifier.send("Happy birthday!", "Happy birthday, dear ${it.name}!", it) }
    }
}
