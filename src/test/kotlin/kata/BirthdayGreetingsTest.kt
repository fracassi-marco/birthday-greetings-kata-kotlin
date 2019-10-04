package kata

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

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
    fun `send greeting to one employee`() {
        val gigi = Employee("Gigi")
        whenever(employeeRepository.bornOn(now().monthValue, now().dayOfMonth)).thenReturn(listOf(gigi))

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier).send("Happy birthday, dear Gigi!", gigi)
    }
}