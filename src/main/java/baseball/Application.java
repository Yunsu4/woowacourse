package baseball;

import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

    public static int BALL = 0;
    public static int STRIKE = 0;
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        inputView.printStartMessage();

        boolean retryGame;
        do {
            List<Integer> randomNumbers = makeRandomNumbers();
            retryGame = playOneGame(inputView, randomNumbers, outputView);
        } while (retryGame);

    }

    private static boolean playOneGame(InputView inputView, List<Integer> randomNumbers, OutputView outputView) {
        do{
            startGame(inputView, randomNumbers, outputView);
            if(STRIKE ==3){
                outputView.printEndMessage();
                String input = inputView.enteredRestart();
                if(input.equals("1")){
                    STRIKE = 0;
                    BALL = 0;
                    return true;
                }
                if(!input.equals("2")){
                    throw new IllegalArgumentException("1 또는 2만 입력해야 합니다.");
                }
                outputView.printEndGame();
            }
        }while(STRIKE != 3);
        return false;
    }

    private static void startGame(InputView inputview, List<Integer> randomNumbers, OutputView outputview) {
        clearResult();
        String userEnteredNumbers = inputview.enteredPredictedNumbers();
        String[] splitNumbers = userEnteredNumbers.split("");
        checkNumberCount(splitNumbers);

        List<Integer> parsedNumbers = parseNumbers(splitNumbers);
        parsedNumbers.forEach(Application::checkNumberRange);
        checkDistinct(parsedNumbers);

        checkBall(parsedNumbers, randomNumbers);
        checkStrike(parsedNumbers, randomNumbers);

        printProcessResult(outputview);

    }

    private static void printProcessResult(OutputView outputView) {
        if(STRIKE !=0 && BALL != 0){
            outputView.printBall(BALL);
            System.out.print(" ");
            outputView.printStrike(STRIKE);
            return;
        }
        if(STRIKE !=0 && BALL == 0){
            outputView.printStrike(STRIKE);
            return;
        }
        if(STRIKE ==0 && BALL != 0){
            outputView.printBall(BALL);
            return;
        }
        outputView.printNothing();
    }

    private static void clearResult(){
        STRIKE = 0;
        BALL = 0;
    }

    private static void checkStrike(List<Integer> parsedNumbers, List<Integer> randomNumbers) {
        for(int index = 0; index < 3; index++){
            if(parsedNumbers.get(index).equals(randomNumbers.get(index))){
                STRIKE++;
                BALL--;
            }
        }
    }

    private static void checkBall(List<Integer> parsedNumbers, List<Integer> randomNumbers) {
        Set<Integer> wholeNumbers = new HashSet<>();
        wholeNumbers.addAll(parsedNumbers);
        wholeNumbers.addAll(randomNumbers);

        System.out.println("입력한 숫자");
        for(int i = 0;i<3;i++){
            System.out.println(parsedNumbers.get(i));
        }
        System.out.println("랜덤 숫자");
        for(int i = 0;i<3;i++){
            System.out.println(randomNumbers.get(i));
        }
        System.out.println("모든 숫자를 합친 길이는: "+wholeNumbers.size());

        if(wholeNumbers.size()!= 6){
            int plusBall = 6-wholeNumbers.size();
            BALL += plusBall;
        }
    }

    private static List<Integer> makeRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
            }
        }
        return randomNumbers;
    }

    private static List<Integer> parseNumbers(String[] splittedNumbers) {
        try{
            return Arrays.stream(splittedNumbers)
                    .mapToInt(Integer::parseInt)
                    .boxed().toList();
        }catch(NumberFormatException e){
            throw new NumberFormatException("1~9 범위의 정수만 입력해야 합니다.");
        }
    }

    private static void checkNumberCount(String[] splitNumbers) {
        if(splitNumbers.length != 3){
            throw new IllegalArgumentException("서로 다른 3개의 수를 입력해야 합니다.");
        }
    }

    private static void checkDistinct(List<Integer> predictedNumbers) {
        Set<Integer> distinctNumbers = new HashSet<>(predictedNumbers);
        if(distinctNumbers.size()!= predictedNumbers.size()){
            throw new IllegalArgumentException("서로 다른 3개의 수를 입력해야 합니다.");
        }
    }

    private static void checkNumberRange(Integer number){
        if(number>9 || number<1){
            throw new IllegalArgumentException("1~9 범위의 정수만 입력 가능합니다.");
        }
    }
}
