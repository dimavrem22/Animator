package cs3500.animator.provider.controller;

import cs3500.animator.view.IView;
import java.util.Objects;

public class FeaturesImpl implements Features {

  private IView delegate;

  public FeaturesImpl(IView delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  @Override
  public void beginAnimation() {
    System.out.println("BEGIN");
    delegate.play();

  }

  @Override
  public void resumeAnimation() {
    delegate.play();

  }

  @Override
  public void restartAnimation() {
    System.out.println("restart");
    delegate.restart();

  }

  @Override
  public void pauseAnimation() {
    delegate.stop();

  }

  @Override
  public void fastForward(int timeSpeedUp) {
    delegate.increaseTempo(timeSpeedUp);

  }

  @Override
  public void fastBackward(int timeSlowDown) {
    delegate.decreaseTempo(timeSlowDown);

  }

  @Override
  public void loopAnimation() {
    delegate.toggleLooping();

  }
}
