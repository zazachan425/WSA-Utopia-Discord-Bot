package tw.waterballsa.utopia.testproject

import dev.minn.jda.ktx.interactions.commands.Subcommand
import dev.minn.jda.ktx.interactions.commands.subcommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.stereotype.Component
import tw.waterballsa.utopia.jda.UtopiaListener
@Component
class TestListener : UtopiaListener() {
    override fun commands(): List<CommandData> {
        return listOf(
            Commands.slash("test", "Start a new guess num game.")
        )
    }

    //test
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        with(event) {
            if (fullCommandName != "test") {
                return
            }
            //do command
            reply("test").queue()
        }
    }
}
