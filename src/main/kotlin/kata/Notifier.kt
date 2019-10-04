package kata

interface Notifier {
    fun send(message: String, recipient: Employee)
}
