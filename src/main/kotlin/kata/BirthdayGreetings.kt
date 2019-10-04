package kata

import java.time.LocalDateTime.now

class BirthdayGreetings(private val employeeRepository: EmployeeRepository, private val notifier: Notifier) {

    fun start() {
        employeeRepository
            .bornOn(now().monthValue, now().dayOfMonth)
            .forEach { notifier.send("Happy birthday, dear ${it.name}!", it) }
    }
}
