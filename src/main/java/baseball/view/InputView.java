package baseball.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public void printStartMessage() {
        System.out.print("숫자 야구 게임을 시작합니다.");
    }

    public String enteredPredictedNumbers() {
        System.out.print("\n숫자를 입력해주세요 : ");
        return getInput();
    }

    public String enteredRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return getInput();
    }

    private String getInput() {
        return Console.readLine();
    }



}
