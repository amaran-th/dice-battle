package dice_battle.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final int MIN_PARTICIPANT_COUNT = 2;
    private List<Participant> participants;

    public Participants(final String participantsName) {
        final Names names = new Names(participantsName);
        if (names.calculateSize() < MIN_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException("참여자 수는 최소 2명 이상이어야 합니다.");
        }
        this.participants = names.getNames()
                .stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
