package kata

import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage



class EmailNotifier(private val smtp: Smtp) : Notifier {

    override fun send(subject: String, message: String, recipient: Employee) {
        val props = Properties()
        props["mail.smtp.host"] = smtp.host
        props["mail.smtp.port"] = smtp.port.toString()
        val session = Session.getInstance(props, null)
        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress("sender@email.com"))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient.email))
        msg.subject = subject
        msg.setText(message)
        Transport.send(msg)
    }

}
