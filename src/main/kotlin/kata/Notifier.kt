package kata

interface Notifier {
    fun send(title: String, message: String, recipient: Employee)
}
