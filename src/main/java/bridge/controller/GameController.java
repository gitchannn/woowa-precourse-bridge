package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class GameController {
    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();

    public void play() {
        // 다리 생성
        outputView.printStartGame();
        List<String> bridge = setBridgeSize();
        System.out.println(bridge);

        // 다리 게임
        int attemptsNumber = 1;
        for (int index = 0; index < bridge.size(); index++) {
            if (isUserSelectionCorrect(setUserSelection(), bridge.get(index))) {
                continue;
            }
        }


    }

    private List<String> setBridgeSize() {
        try {
            outputView.printBridgeSizeInput();
            BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
            BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
            return bridgeMaker.makeBridge(inputView.readBridgeSize());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            setBridgeSize();
        }
        return null;
    }

    private String setUserSelection() {
        try {
            outputView.printMoveInput();
            return inputView.readMoving();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return setUserSelection();
        }
    }

    private boolean isUserSelectionCorrect(String userSelection, String bridge) {
        return userSelection.equals(bridge);
    }
}
