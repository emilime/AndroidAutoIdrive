package me.hufman.androidautoidrive.music

import android.os.SystemClock
import kotlin.math.min

/** Given a snapshot of playback position, return the song's current playback position in ms */
class PlaybackPosition(val playbackPaused: Boolean,     // basically whether to show a Play button and blink the time
                       val lastPositionUpdateTime: Long = SystemClock.elapsedRealtime(),
                       val lastPosition: Long,
                       val maximumPosition: Long) {
	fun getPosition(): Long {
		return if (playbackPaused || lastPositionUpdateTime == 0L) {
			lastPosition
		} else {
			val estimatedPosition = lastPosition + (SystemClock.elapsedRealtime() - lastPositionUpdateTime)
			if (maximumPosition >= 0) {
				min(estimatedPosition, maximumPosition)
			} else {
				estimatedPosition
			}
		}
	}
}