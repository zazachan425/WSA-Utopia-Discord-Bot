package tw.waterballsa.utopia.utopiagamificationquest.domain

import java.util.*
import java.util.UUID.randomUUID

class Mission(val id: UUID, val player: Player, val quest: Quest) {
    constructor(player: Player, quest: Quest) : this(randomUUID(), player, quest)

    fun match(action: Action): Boolean = action.match(quest.criteria)

    fun carryOut(action: Action) {
        action.execute(quest.criteria)
    }

    fun isCompleted(): Boolean = quest.criteria.isCompleted

    fun rewardPlayer() = player.gainExp(quest.reward.exp)

    fun nextMission(): Mission? {
        if (isCompleted().not()) {
            return null
        }
        return quest.nextQuest?.let { Mission(player, it) }
    }
}
