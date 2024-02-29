package dice_battle.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputParticipantNames() {
        System.out.println("> 게임에 참여할 참여자 목록을 입력해주십시오.");
        return scanner.nextLine();
    }

    public static String inputAttackTarget() {
        System.out.println("> 공격할 대상을 입력해주십시오.");
        return scanner.nextLine();
    }

    public static void inputEnter1() {
        System.out.println("> 엔터를 눌러 주사위를 굴려주십시오");
        System.out.println("첫 번째 주사위 값 :");
        scanner.nextLine();
    }

    public static void inputEnter2() {
        System.out.println("두 번째 주사위 값 :");
        scanner.nextLine();
    }

}
