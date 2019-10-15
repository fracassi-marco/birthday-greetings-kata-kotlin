package kata

interface Notifier {
    fun send(subject: String, message: String, recipient: Employee)
}
