package kata

import com.nhaarman.mockitokotlin2.*
import kata.Date.Companion.today
import org.junit.jupiter.api.Test

class BirthdayGreetingsTest {

    private val employeeRepository: EmployeeRepository = mock()
    private val notifier: Notifier = mock()

    @Test
    fun `no employees`() {
        whenever(employeeRepository.bornOn(today())).thenReturn(emptyList())

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier, never()).send(any(), any(), any())
    }

    @Test
    fun `send greetings to one employee`() {
        val gigi = Employee("Gigi", today(), "a@b.c")
        whenever(employeeRepository.bornOn(today())).thenReturn(listOf(gigi))

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier).send("Happy birthday!", "Happy birthday, dear Gigi!", gigi)
    }

    @Test
    fun `send greetings to multiple employees`() {
        val gigi = Employee("Gigi", today(), "a@b.c")
        val vito = Employee("Vito", today(), "a@b.c")
        val alex = Employee("Alex", today(), "a@b.c")
        whenever(employeeRepository.bornOn(today())).thenReturn(listOf(gigi, vito, alex))

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier).send(any(), any(), eq(gigi))
        verify(notifier).send(any(), any(), eq(vito))
        verify(notifier).send(any(), any(), eq(alex))
    }
}