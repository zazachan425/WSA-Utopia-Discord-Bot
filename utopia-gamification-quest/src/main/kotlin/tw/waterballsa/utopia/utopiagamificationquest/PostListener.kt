package tw.waterballsa.utopia.utopiagamificationquest

import net.dv8tion.jda.api.events.guild.GuildAuditLogEntryCreateEvent
import org.springframework.stereotype.Component
import tw.waterballsa.utopia.commons.config.WsaDiscordProperties
import tw.waterballsa.utopia.jda.UtopiaListener
import tw.waterballsa.utopia.utopiagamificationquest.domain.Player
import tw.waterballsa.utopia.utopiagamificationquest.domain.actions.PostAction
import tw.waterballsa.utopia.utopiagamificationquest.extensions.claimMissionReward
import tw.waterballsa.utopia.utopiagamificationquest.service.PlayerFulfillMissionsService

@Component
class PostListener(
    private val properties: WsaDiscordProperties,
    private val playerFulfillMissionsService: PlayerFulfillMissionsService
) : UtopiaListener() {

    override fun onGuildAuditLogEntryCreate(event: GuildAuditLogEntryCreateEvent) {
        with(event) {
            val player = user ?: return

            playerFulfillMissionsService.execute(action) { completedMission ->
                player.claimMissionReward(completedMission)
            }
        }
    }

    private val GuildAuditLogEntryCreateEvent.user get() = entry.user ?: jda.retrieveUserById(entry.userId).complete()

    private val GuildAuditLogEntryCreateEvent.action
        get() = PostAction(Player(user.id, user.name), properties.flagPostChannelId)

}
