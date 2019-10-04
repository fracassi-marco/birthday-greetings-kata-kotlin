package kata

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

class BirthdayGreetingsTest {

    private val employeeRepository: EmployeeRepository = mock()
    private val notifier: Notifier = mock()

    @Test
    fun `no employees`() {
        whenever(employeeRepository.bornOn(now().monthValue, now().dayOfMonth)).thenReturn(emptyList())

        BirthdayGreetings(employeeRepository, notifier).start()

        verify(notifier, never()).send()
    }
}