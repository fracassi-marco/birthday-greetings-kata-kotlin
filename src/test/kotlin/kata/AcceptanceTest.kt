package kata

import com.dumbster.smtp.SimpleSmtpServer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate.now

private const val EMAIL_SERVER_PORT = 8877

class AcceptanceTest {

    private lateinit var emailServer: SimpleSmtpServer

    private val file = File("/tmp/employees.txt")

    @BeforeEach
    fun setUp() {
        file.delete()
        emailServer = SimpleSmtpServer.start(EMAIL_SERVER_PORT)
    }

    @AfterEach
    fun tearDown() {
        file.delete()
        emailServer.stop()
    }

    @Test
    fun `send greetings to one employee`() {
        file.writeText("last_name, first_name, date_of_birth, email")
        file.appendText("\nDoe, John, 1982/${currentMonth()}/${currentDay()}, john.doe@foobar.com")
        file.appendText("\nAnn, Mary, 1975/09/11, mary.ann@foobar.com")

        BirthdayGreetings(FileEmployeeRepository(File("/tmp/employees.txt")), EmailNotifier(Smtp("localhost", EMAIL_SERVER_PORT))).start()

        val email = emailServer.receivedEmails.single()
        Assertions.assertThat(email.body).isEqualTo("Happy birthday, dear John!")
    }

    private fun currentDay() = now().dayOfMonth

    private fun currentMonth() = now().monthValue
}