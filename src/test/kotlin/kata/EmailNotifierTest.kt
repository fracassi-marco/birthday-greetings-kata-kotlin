package kata

import com.dumbster.smtp.SimpleSmtpServer
import kata.Date.Companion.today
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate.now


private const val EMAIL_SERVER_PORT = 7878

class EmailNotifierTest {

    private lateinit var emailServer: SimpleSmtpServer

    @BeforeEach
    fun setUp() {
        emailServer = SimpleSmtpServer.start(EMAIL_SERVER_PORT)
    }

    @AfterEach
    fun tearDown() {
        emailServer.stop()
    }

    @Test
    fun `should send email`() {
        EmailNotifier(Smtp("localhost", EMAIL_SERVER_PORT)).send("subject", "text", Employee("ignore", today(), "foo@bar.baz"))

        val email = emailServer.receivedEmails.single()

        assertThat(email.getHeaderValue("Subject")).isEqualTo("subject")
        assertThat(email.body).isEqualTo("text")
        assertThat(email.getHeaderValue("To")).isEqualTo("foo@bar.baz")
    }
}