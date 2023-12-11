package com.futo.platformplayer.api.media.models.live

import com.caoccao.javet.values.reference.V8ValueObject
import com.futo.platformplayer.engine.IV8PluginConfig
import com.futo.platformplayer.getOrThrow

interface IPlatformLiveEvent {
    val type : LiveEventType;


    companion object {
        fun fromV8(config: IV8PluginConfig, obj: V8ValueObject, contextName: String = "LiveEvent") : IPlatformLiveEvent {
            val t = LiveEventType.fromInt(obj.getOrThrow<Int>(config, "type", contextName));
            return when(t) {
                LiveEventType.COMMENT -> LiveEventComment.fromV8(config, obj);
                LiveEventType.EMOJIS -> LiveEventEmojis.fromV8(config, obj);
                LiveEventType.DONATION -> LiveEventDonation.fromV8(config, obj);
                LiveEventType.VIEWCOUNT -> LiveEventViewCount.fromV8(config, obj);
                LiveEventType.RAID -> LiveEventRaid.fromV8(config, obj);
                else -> throw NotImplementedError("Unknown type $t");
            }
        }
    }
}