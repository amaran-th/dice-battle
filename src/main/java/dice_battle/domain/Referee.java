package dice_battle.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Referee {

    private Participants participants;
    private Ranking ranking;

    public Referee(final Participants participants) {
        this.participants = participants;
        this.ranking = new Ranking(participants.size());
    }

    public Map<Name, Integer> getParticipantSpeed() {
        return participants.decideParticipantsSpeed();
    }

    public void validateTarget(final Name attacker, final Name target) {
        if (attacker.equals(target)) {
            throw new IllegalArgumentException("자기 자신을 공격할 수 없습니다.");
        }
        participants.validateParticipantName(target);
    }

    public void attackParticipant(final Name attacker, final Name target, final int damage) {
        participants.attackParticipant(target, damage);
        if (participants.isDead(target)) {
            ranking.rank(target);
        }
        if (ranking.isAllParticipantsWithoutFirstRanking()) {
            ranking.rank(attacker);
        }
    }

    public boolean isAllParticipantsRanking() {
        return ranking.isAllParticipantsRanking();
    }

    public boolean isDead(final Name name) {
        return participants.isDead(name);
    }

    public List<Name> getSortedParticipantNames(final Map<Name, Integer> participantSpeed) {
        final List<Name> sortedNames = participantSpeed.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .map(Entry::getKey)
                .collect(Collectors.toList());
        Collections.reverse(sortedNames);
        return sortedNames;
    }

    public List<Name> findSurvivorNames() {
        return participants.findSurvivorNames();
    }

    public Participants getParticipants() {
        return participants;
    }

    public Ranking getRanking() {
        return ranking;
    }
}
