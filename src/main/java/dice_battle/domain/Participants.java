package dice_battle.domain;

import dice_battle.utils.Dice;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private static final int MIN_PARTICIPANT_COUNT = 2;
    private List<Participant> participants;

    public Participants(final String participantsName) {
        final Names names = new Names(participantsName);
        validateCount(names);
        validateDuplicate(names);
        this.participants = names.getNames()
                .stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private void validateCount(Names names) {
        if (names.calculateSize() < MIN_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException("참여자 수는 최소 2명 이상이어야 합니다.");
        }
    }

    private void validateDuplicate(Names names) {
        if (names.existDuplicate()) {
            throw new IllegalArgumentException("같은 이름의 참여자는 2명 이상 존재할 수 없습니다.");
        }
    }

    public Map<Name, Integer> decideParticipantsSpeed() {
        final Map<Name, Integer> result = new LinkedHashMap<>();
        participants.forEach(participant -> {
            if (participant.isAlive()) {
                result.put(participant.getName(), Dice.roll());
            }
        });
        return result;
    }

    public void attackParticipant(final Name name, final int damage) {
        participants.stream()
                .filter(participant -> participant.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자입니다."))
                .sufferDamage(damage);
    }

    public void validateParticipantName(final Name targetName) {
        final Participant target = participants.stream()
                .filter(participant -> participant.getName().equals(targetName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자입니다."));
        if (!target.isAlive()) {
            throw new IllegalArgumentException("이미 전투불능이 된 참여자를 지목할 수 없습니다.");
        }
    }

    public boolean isDead(final Name name) {
        return !participants.stream()
                .filter(participant -> participant.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자입니다."))
                .isAlive();

    }

    public List<Name> findSurvivorNames() {
        return participants.stream()
                .filter(Participant::isAlive)
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public int size() {
        return participants.size();
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
