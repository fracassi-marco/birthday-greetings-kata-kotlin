package kata

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import java.time.LocalDate.now

class BirthdayGreetingsTest {

    private val employeeRepository: EmployeeRepository = mock()
    private val notifier: Notifier = mock()

    @Test
    fun `no employees`() {
        whenever(employeeRepository.bornOn(now().monthValue, now().dayOfMonth)).thenReturn(emptyList())

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier, never()).send(any(), any())
    }

    @Test
    fun `send greetings to one employee`() {
        val gigi = Employee("Gigi", now())
        whenever(employeeRepository.bornOn(now().monthValue, now().dayOfMonth)).thenReturn(listOf(gigi))

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier).send("Happy birthday, dear Gigi!", gigi)
    }

    @Test
    fun `send greetings to multiple employees`() {
        val gigi = Employee("Gigi", now())
        val vito = Employee("Vito", now())
        val alex = Employee("Alex", now())
        whenever(employeeRepository.bornOn(now().monthValue, now().dayOfMonth)).thenReturn(listOf(gigi, vito, alex))

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier).send("Happy birthday, dear Gigi!", gigi)
        verify(notifier).send("Happy birthday, dear Vito!", vito)
        verify(notifier).send("Happy birthday, dear Alex!", alex)
    }
}