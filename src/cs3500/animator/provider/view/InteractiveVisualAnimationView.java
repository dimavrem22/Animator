package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.IController;
import cs3500.animator.provider.controller.Features;
import cs3500.animator.provider.model.IReadAnimationModel;
import cs3500.animator.provider.view.*;
import cs3500.animator.provider.model.ViewModel;
import cs3500.animator.view.VisualAnimationView;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class adds the interactivity feature to the visual view by adding more features to
 * the existing implementation. It contains methods such as start, pause, resume and restart
 * which are the new features that will be called on using the buttons and keys. For example,
 * the s key will start animation, the p key will pause the animation, the r key will resume
 * the animation, the e key will restart, the q key will quit the animation, the n key will
 * decrease speed, m key will increase speed and l will loop the animation. Similarly,
 * the buttons with those titles will perform the corresponding actions.
 */
public class InteractiveVisualAnimationView implements IVisualAnimationView {
  private InteractivePanel panel;
  private InteractiveFrame frame;
  private IVisualAnimationView delegate;


  /**
   * A constructor that takes in the view model and speed so that we are able to pass in the
   * view model in the delegate since we want this view to have all the same features as the
   * visual view without having to re do all the methods that already exist there.
   * Additionally, it also has objects of the panel and frame classes so that
   * we are able to access both of those fields and use them to implement this class.
   * @param viewModel the model that we want to be interactive we would pass it in this constructor
   * @param speed the speed at which we would want our interactive animation to render at
   */
  public InteractiveVisualAnimationView(IReadAnimationModel viewModel, int speed) {
    Objects.requireNonNull(viewModel);
    this.panel = new InteractivePanel(speed, viewModel);
    this.frame = new InteractiveFrame(this.panel, viewModel.getWidthCanvas(),
        viewModel.getHeightCanvas());
    this.delegate = new VisualAnimationView(this.panel, this.frame, viewModel);
  }

  protected InteractiveVisualAnimationView() {
    // this view is used in order to construct the mock view
  }

  @Override
  public void render() throws IOException {
    this.delegate.render();
  }

  @Override
  public void startAnimation() {
    System.out.println("Starting timer....");
    this.panel.startTimer();
  }

  @Override
  public void resumeAnimation() {
    System.out.println("Starting timer....");
    this.panel.startTimer();
  }

  @Override
  public void pauseAnimation() {
    //System.out.println(String.format("I was trying to pause 1 :("));
    this.panel.stopPanel();
  }

  @Override
  public void restartAnimation() {
    System.out.println("Rewind....");
    this.panel.rewind();
  }

  @Override
  public void fastForward(int speedUp) {
    this.panel.fastForward(speedUp);
  }

  @Override
  public void fastBackward(int slowDown) {
    this.panel.fastBackward(slowDown);
  }

  @Override
  public void addFeatures(Features feat) {
    if (feat == null) {
      throw new IllegalArgumentException("features object cannot be null");
    }
    this.configureButtonListener(feat);
    this.configureKeyBoardListener(feat);
  }

  @Override
  public void loop() {
    this.panel.looping();
  }

  /**
   * Configures each key press with a corresponding feature to give that key that feature
   * ability. The keys are being place with feature in a map using lambda so that the code
   * is cleaner.
   * @param feat the features which contains the methods that is being applied to to each key
   */
  private void configureKeyBoardListener(Features feat) {
    if (feat == null) {
      throw new IllegalArgumentException("features object cannot be null");
    }

    Map<Integer, Runnable> keyPresses = new HashMap<>();

    // key features
    // start
    keyPresses.put(KeyEvent.VK_S, () -> feat.beginAnimation());
    // pause
    keyPresses.put(KeyEvent.VK_P, () -> feat.pauseAnimation());
    // resume
    keyPresses.put(KeyEvent.VK_R, () -> feat.resumeAnimation());
    // restart
    keyPresses.put(KeyEvent.VK_E, () -> feat.restartAnimation());
    //quit
    keyPresses.put(KeyEvent.VK_Q, () -> System.exit(0));
    // arrow left
    keyPresses.put(KeyEvent.VK_N, () -> feat.fastBackward(2));

    // arrow right
    keyPresses.put(KeyEvent.VK_M, () -> feat.fastForward(2));

    // arrow right
    keyPresses.put(KeyEvent.VK_L, () -> feat.loopAnimation());

    KeyListeners kbd = new KeyListeners();
    kbd.setKeyPressedMap(keyPresses);

    this.panel.addKeyListener(kbd);

    // keys won't work without this
    this.panel.setFocusable(true);
  }

  /**
   * Configures each button with a corresponding feature to give that button that
   * feature ability once the button is pressed. The buttons are being placed with a
   * feature in a map using lambda so that the code is cleaner.
   * @param feat the features which contains the methods that is being applied to to each button
   */
  private void configureButtonListener(Features feat) {
    if (feat == null) {
      throw new IllegalArgumentException("features object cannot be null");
    }

    Map<String,Runnable> buttonClickedMap = new HashMap<String,Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("restart", () -> feat.restartAnimation());
    buttonClickedMap.put("pause", () -> feat.pauseAnimation());
    buttonClickedMap.put("start", () -> feat.beginAnimation());
    buttonClickedMap.put("resume", () -> feat.resumeAnimation());
    buttonClickedMap.put("fast forward", () -> feat.fastForward(2));
    buttonClickedMap.put("fast backward", () -> feat.fastBackward(2));
    buttonClickedMap.put("loop", () -> feat.loopAnimation());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    //System.out.println("BUTTONS");
    this.frame.addActions(buttonListener);
  }
}

