package co.touchlab.sessionize.platform

import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

actual class Date(val date: java.util.Date) {
    actual fun toLongMillis(): Long = date.time
}

actual class DateFormatHelper actual constructor(format: String):KoinComponent {
    private val dateFormatterConference = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat = SimpleDateFormat(format).apply {
            this.timeZone = TimeZone.getTimeZone(get<String>(named("timeZone")))
        }
    }

    private val dateFormatterLocal = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat = SimpleDateFormat(format).apply {
            this.timeZone = TimeZone.getDefault()
        }
    }

    actual fun toConferenceDate(s: String): Date = Date(dateFormatterConference.get()!!.parse(s))

    actual fun toLocalDate(s: String): Date = Date(dateFormatterLocal.get()!!.parse(s))

    actual fun formatConferenceTZ(d: Date): String = dateFormatterConference.get()!!.format(d.date)

    actual fun formatLocalTZ(d: Date): String = dateFormatterLocal.get()!!.format(d.date)
}