package cs3500.animator.provider.view;

import cs3500.animator.provider.model.IReadAnimationModel;
import cs3500.animator.provider.model.ViewModel;
import java.util.Objects;

/**
 * This panel contains all the features of the visual panel except that now it has some more
 * interactive features such as the ability to start, rewind, stop, fast forward etc. This
 * allows us to control the panel accordingly and give the buttons the ability to control
 * the animation using these methods.
 */
public class InteractivePanel extends VisualPanel {

  /**
   * Since the panel extends the visual panel, the visual panel fields are stored as a super.
   * Takes in the speed at which we want the panel to render at and the view model for which
   * we want to run the panel for.
   * @param speed at which we want the panel to render at
   * @param viewModel the model for which we want it to be interactive for
   */
  public InteractivePanel(int speed, IReadAnimationModel viewModel) {
    super(speed, Objects.requireNonNull(viewModel));
  }

  @Override
  public void startTimerRender() {
    super.startTimerRender();
    //throw new UnsupportedOperationException();
  }

  @Override
  public void startTimer() {
    if (!this.timer.isRunning()) {
      this.timer.start();
    }
  }

  @Override
  public void rewind() {
    this.currentTick = 1;
    timer.restart();
  }

  @Override
  public void stopPanel() {
    this.timer.stop();
  }

  @Override
  public void fastForward(int speedUp) {
    System.out.println(String.format("speed: %d", this.speed));
    this.speed = this.speed * 2;
    this.updateTimer();
    //this.timer.restart();
  }

  @Override
  public void fastBackward(int slowDown) {
    //System.out.println(String.format("speed: %d", this.speed));
    this.speed = this.speed / 2;
    this.updateTimer();
    //this.timer.restart();
  }

  @Override
  public void looping() {
    this.looping = !this.looping;
  }
}
