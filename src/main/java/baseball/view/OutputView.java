package baseball.view;

public class OutputView {

    public void printStrike(int strike) {
        System.out.print(strike+"스트라이크");
    }

    public void printBall(int ball) {
        System.out.print(ball+"볼");
    }

    public void printNothing() {
        System.out.print("낫싱");
    }

    public void printEndMessage() {
        System.out.println("\n3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public void printEndGame() {
        System.out.println("게임 종료");
    }

}
